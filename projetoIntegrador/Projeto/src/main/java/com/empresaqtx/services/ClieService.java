package com.empresaqtx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresaqtx.entities.Cliente;
import com.empresaqtx.repository.ClieRepository;

@Service
public class ClieService {
private final ClieRepository clieRepository;
	
	@Autowired
	public ClieService(ClieRepository clieRepository) {
		this.clieRepository = clieRepository;
	}
	
	public List<Cliente> buscaTodosCliente(){
		return clieRepository.findAll();
	}
	
	public Cliente BuscaClienteId(Long id) {
		Optional <Cliente> Cliente = clieRepository.findById(id);
		return Cliente.orElse(null);
	}
	
	public Cliente salvaCliente(Cliente cliente) {
		return clieRepository.save(cliente);	
	}
	
	public Cliente alterarCliente (Long id, Cliente alterarC) {
		Optional <Cliente> existeCliente = clieRepository.findById(id);
		if (existeCliente.isPresent()) {
			alterarC.setId(id);
			return clieRepository.save(alterarC);
		}
		return null;
	}
	
	public boolean apagarCliente(Long id) {
		Optional <Cliente> existeCliente = clieRepository.findById(id);
		if (existeCliente.isPresent()) {
			clieRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

