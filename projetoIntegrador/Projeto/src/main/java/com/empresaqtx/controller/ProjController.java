package com.empresaqtx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaqtx.entities.Projeto;
import com.empresaqtx.services.ProjService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Projetos", description = "API REST DE GERNECIAMENTO DE PROJETOS")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/projetos") 
	public class ProjController {
	private final ProjService projService;

	@Autowired
	public ProjController(ProjService projService) {
		this.projService = projService;
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza Projeto por ID")
	public ResponseEntity<Projeto> buscaProjetoControlId(@PathVariable Long id){
		Projeto projeto = projService.buscaProjetoId(id);
		if(projeto != null) {
			return ResponseEntity.ok(projeto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todos os Projetos")
	public ResponseEntity<List<Projeto>> buscaTodosProjetoControl(){
		List<Projeto> Projetos = projService.buscaTodosProjetos();
		return ResponseEntity.ok(Projetos);
	}

	@PostMapping
	@Operation (summary = "Cadastra um Projeto")
	public ResponseEntity<Projeto> salvaProjetosControl(@RequestBody @Valid Projeto projeto){
		Projeto salvaProjeto = projService.salvaProjeto(projeto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProjeto);
	}

	@PutMapping("/{id}")
	@Operation (summary = "Altera um Projeto")
	public ResponseEntity<Projeto> alteraProjetoControlId(@PathVariable Long id,@RequestBody @Valid Projeto projeto ){
		Projeto alteraProjeto = projService.alterarProjeto(id, projeto);
		if(alteraProjeto != null) {
			return ResponseEntity.ok(projeto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Apaga um Projeto")
	public ResponseEntity<Projeto> apagaProjetoControl(@PathVariable Long id){
		boolean apagar = projService.apagarProjeto(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {	
			return ResponseEntity.notFound().build();
		}
	}
}

