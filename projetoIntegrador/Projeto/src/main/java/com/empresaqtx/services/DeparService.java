package com.empresaqtx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresaqtx.entities.Departamento;
import com.empresaqtx.repository.DeparRepository;

@Service
public class DeparService {
	private final DeparRepository deparRepository;

	@Autowired
	public DeparService(DeparRepository deparRepository) {
		this.deparRepository = deparRepository;
	}

	public List<Departamento> buscaTodosDepartamentos(){
		return deparRepository.findAll();
	}

	public Departamento buscaDepartamentoId(Long id) {
		Optional<Departamento> Departamento = deparRepository.findById(id);
		return Departamento.orElse(null);
	}

	public Departamento salvaDepartamento(Departamento departamento) {
		return deparRepository.save(departamento);
	}

	public Departamento alterarDepartamento(Long id, Departamento alterarD) {
		Optional<Departamento> existeDepartamento = deparRepository.findById(id);
		if(existeDepartamento.isPresent()) {
			alterarD.setId(id);
			return deparRepository.save(alterarD);
		}
		return null;
	}

	public boolean apagarDepartamento(Long id) {
		Optional<Departamento> existeDepartamento = deparRepository.findById(id);
		if(existeDepartamento.isPresent()) {
			deparRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
