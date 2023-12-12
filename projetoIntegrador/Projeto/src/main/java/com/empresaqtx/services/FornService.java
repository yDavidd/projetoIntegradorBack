package com.empresaqtx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresaqtx.entities.Fornecedor;
import com.empresaqtx.repository.FornRepository;

@Service
public class FornService {

private final FornRepository fornRepository;
	
	@Autowired
	public FornService(FornRepository fornRepository) {
		this.fornRepository = fornRepository;
	}
	
	public List<Fornecedor> buscaTodosFornecedor(){
		return fornRepository.findAll();
	}
	
	public Fornecedor BuscaFornecedorId(Long id) {
		Optional <Fornecedor> Fornecedor = fornRepository.findById(id);
		return Fornecedor.orElse(null);
	}
	
	public Fornecedor salvaFornecedor(Fornecedor fornecedor) {
		return fornRepository.save(fornecedor);	
	}
	
	public Fornecedor alterarFornecedor (Long id, Fornecedor alterarF) {
		Optional <Fornecedor> existeFornecedor = fornRepository.findById(id);
		if (existeFornecedor.isPresent()) {
			alterarF.setId(id);
			return fornRepository.save(alterarF);
		}
		return null;
	}
	
	public boolean apagarFornecedor(Long id) {
		Optional <Fornecedor> existeFornecedor = fornRepository.findById(id);
		if (existeFornecedor.isPresent()) {
			fornRepository.deleteById(id);
			return true;
		}
		return false;
	}
}