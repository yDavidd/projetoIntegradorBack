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

import com.empresaqtx.entities.Fornecedor;
import com.empresaqtx.services.FornService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Fornecedor" , description = "API REST DE GERENCIAMENTO DE FORNECEDORES")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/fornecedores")
public class FornController {

	private final FornService fornService;

	@Autowired
	public FornController (FornService fornService) {
		this.fornService = fornService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza o fornecedor pelo ID")
	public ResponseEntity<Fornecedor> buscaFornecedorControlId(@PathVariable Long id){
		Fornecedor fornecedor = fornService.BuscaFornecedorId(id);
		if (fornecedor != null) {
			return ResponseEntity.ok(fornecedor);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "Apresenta todos os fornecedores")
	public ResponseEntity<List<Fornecedor>> buscaTodosFornecedorCOntrol(){
		List<Fornecedor> Fornecedor = fornService.buscaTodosFornecedor();
		return ResponseEntity.ok(Fornecedor);
	}

	@PostMapping
	@Operation(summary = "Cadastra um fornecedor")
	public ResponseEntity<Fornecedor> salvaFornecedorControl (@RequestBody @Valid Fornecedor fornecedor){
		Fornecedor salvaFornecedor = fornService.salvaFornecedor(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaFornecedor);
	}
	@PutMapping("/{id}")
	@Operation(summary = "Altera os Fornecedores")
	public ResponseEntity<Fornecedor> alterarFornecedorControl(@PathVariable Long id, @RequestBody @Valid Fornecedor fornecedor){
		Fornecedor alterarFornecedor = fornService.alterarFornecedor(id, fornecedor);
		if(alterarFornecedor != null) {
			return ResponseEntity.ok(fornecedor);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui os fornecedores")
	public ResponseEntity<Fornecedor> apagaFornecedorControl (@PathVariable Long id){
		boolean apagar = fornService.apagarFornecedor(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
