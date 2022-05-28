package com.eontecnologia.eonlog.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eontecnologia.eonlog.domain.model.Entrega;
import com.eontecnologia.eonlog.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	private SolicitacaoEntregaService solicitacaoEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		return solicitacaoEntregaService.solicitar(entrega);
		//Teste dev tools.
	}

}
