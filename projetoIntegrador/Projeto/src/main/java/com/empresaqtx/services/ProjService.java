package com.empresaqtx.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresaqtx.entities.Projeto;
import com.empresaqtx.repository.ProjRepository;

@Service
public class ProjService {
	private final ProjRepository projRepository;

	@Autowired
	public ProjService(ProjRepository projRepository) {
		this.projRepository = projRepository;
	}

	public List<Projeto> buscaTodosProjetos(){
		return projRepository.findAll();
	}

	public Projeto buscaProjetoId(Long id) {
		Optional<Projeto> Projeto = projRepository.findById(id);
		return Projeto.orElse(null);
	}

	public Projeto salvaProjeto(Projeto projeto) {
		return projRepository.save(projeto);
	}

	public Projeto alterarProjeto(Long id, Projeto alterarP) {
		Optional<Projeto> existeProjeto = projRepository.findById(id);
		if(existeProjeto.isPresent()) {
			alterarP.setId(id);
			return projRepository.save(alterarP);
		}
		return null;
	}

	public boolean apagarProjeto(Long id) {
		Optional<Projeto> existeProjeto = projRepository.findById(id);
		if(existeProjeto.isPresent()) {
			projRepository.deleteById(id);
			return true;
		}
		return false;
	}

}