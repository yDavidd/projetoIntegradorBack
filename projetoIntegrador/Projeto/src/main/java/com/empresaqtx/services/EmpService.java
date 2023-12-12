package com.empresaqtx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresaqtx.entities.Empresa;
import com.empresaqtx.repository.EmpRepository;

@Service
public class EmpService {
	private final EmpRepository empRepository;

	@Autowired
	public EmpService(EmpRepository empRepository) {
		this.empRepository = empRepository;
	}

	public List<Empresa> buscaTodosEmpresas(){
		return empRepository.findAll();
	}

	public Empresa buscaEmpresaId(Long id) {
		Optional<Empresa> Empresa = empRepository.findById(id);
		return Empresa.orElse(null);
	}

	public Empresa salvaEmpresa(Empresa empresa) {
		return empRepository.save(empresa);
	}

	public Empresa alterarEmpresa(Long id, Empresa alterarEmpresa) {
		Optional<Empresa> existeEmpresa = empRepository.findById(id);
		if(existeEmpresa.isPresent()) {
			alterarEmpresa.setId(id);
			return empRepository.save(alterarEmpresa);
		}
		return null;
	}

	public boolean apagarEmpresa(Long id) {
		Optional<Empresa> existeEmpresa = empRepository.findById(id);
		if(existeEmpresa.isPresent()) {
			empRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
