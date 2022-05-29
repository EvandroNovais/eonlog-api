package com.eontecnologia.eonlog.domain.service;

import com.eontecnologia.eonlog.domain.model.Cliente;
import com.eontecnologia.eonlog.domain.model.Entrega;
import com.eontecnologia.eonlog.domain.model.StatusEntrega;
import com.eontecnologia.eonlog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregaRepository;	
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		return entregaRepository.save(entrega);
	}

}
