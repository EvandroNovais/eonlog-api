package com.eontecnologia.eonlog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eontecnologia.eonlog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Campo> campos = new ArrayList<>();		
		
		for(ObjectError erro : ex.getBindingResult().getAllErrors()){
			String nome = ((FieldError)erro).getField();
			String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			
			campos.add(new Campo(nome, mensagem));			
		}
		
		Error error = new Error();
		error.setStatus(status.value());
		error.setDataHora(OffsetDateTime.now());
		error.setTitulo("Um ou mais campos inválidos, preencha corretamente e tente novamente");
		error.setCampos(campos);
		
		return handleExceptionInternal(ex, error, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Error error = new Error();
		error.setStatus(status.value());
		error.setDataHora(OffsetDateTime.now());
		error.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
	
}
