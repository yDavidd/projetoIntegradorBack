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

import com.empresaqtx.entities.Funcionario;
import com.empresaqtx.services.FunService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Funcionario", description = "API REST DE GERNECIAMENTO DE Funcionario")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/funcionarios") 
public class FunController {
	private final FunService funService;

	@Autowired
	public FunController(FunService funService) {
		this.funService = funService;
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza funcioanrio por ID")
	public ResponseEntity<Funcionario> buscaFuncionarioControlId(@PathVariable Long id){
		Funcionario funcionario = funService.buscaFuncionarioId(id);
		if(funcionario != null) {
			return ResponseEntity.ok(funcionario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todos os Funcionários")
	public ResponseEntity<List<Funcionario>> buscaTodosFuncionarioControl(){
		List<Funcionario> Funcionarios = funService.buscaTodosFuncionarios();
		return ResponseEntity.ok(Funcionarios);
	}

	@PostMapping
	@Operation (summary = "Cadastra um Funcionário")
	public ResponseEntity<Funcionario> salvaFuncionariosControl(@RequestBody Funcionario funcionario){
		Funcionario salvaFuncionario = funService.salvaFuncionario(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaFuncionario);
	}

	@PutMapping("/{id}")
	@Operation (summary = "Altera um Funcionário")
	public ResponseEntity<Funcionario> alteraFuncionarioControlId(@PathVariable Long id,@RequestBody @Valid Funcionario funcionario ){
		Funcionario alteraFuncionario = funService.alterarFuncionario(id, funcionario);
		if(alteraFuncionario != null) {
			return ResponseEntity.ok(funcionario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Apaga um Funcionario")
	public ResponseEntity<Funcionario> apagaFuncionarioControl(@PathVariable Long id){
		boolean apagar = funService.apagarFuncionario(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {	
			return ResponseEntity.notFound().build();
		}
	}
}
