package com.empresaqtx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresaqtx.entities.Funcionario;
import com.empresaqtx.repository.FunRepository;


@Service
public class FunService {
	private final FunRepository funRepository;

	@Autowired
	public FunService(FunRepository funRepository) {
		this.funRepository = funRepository;
	}

	public List<Funcionario> buscaTodosFuncionarios(){
		return funRepository.findAll();
	}

	public Funcionario buscaFuncionarioId(Long id) {
		Optional<Funcionario> Funcionario = funRepository.findById(id);
		return Funcionario.orElse(null);
	}

	public Funcionario salvaFuncionario(Funcionario funcionario) {
		return funRepository.save(funcionario);
	}

	public Funcionario alterarFuncionario(Long id, Funcionario alterarF) {
		Optional<Funcionario> existeFuncionario = funRepository.findById(id);
		if(existeFuncionario.isPresent()) {
			alterarF.setId(id);
			return funRepository.save(alterarF);
		}
		return null;
	}

	public boolean apagarFuncionario(Long id) {
		Optional<Funcionario> existeFuncionario = funRepository.findById(id);
		if(existeFuncionario.isPresent()) {
			funRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
