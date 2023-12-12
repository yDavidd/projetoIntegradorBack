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

import com.empresaqtx.entities.Departamento;
import com.empresaqtx.services.DeparService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Departamentos", description = "API REST DE GERNECIAMENTO DE DEPARTAMENTOS")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/departamentos") 
	public class DeparController {
	private final DeparService deparService;

	@Autowired
	public DeparController(DeparService deparService) {
		this.deparService = deparService;
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza Departamento por ID")
	public ResponseEntity<Departamento> buscaDepartamentoControlId(@PathVariable Long id){
		Departamento departamento = deparService.buscaDepartamentoId(id);
		if(departamento != null) {
			return ResponseEntity.ok(departamento);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todos os Departamentos")
	public ResponseEntity<List<Departamento>> buscaTodosDepartamentoControl(){
		List<Departamento> Departamentos = deparService.buscaTodosDepartamentos();
		return ResponseEntity.ok(Departamentos);
	}

	@PostMapping
	@Operation (summary = "Cadastra um Departamento")
	public ResponseEntity<Departamento> salvaDepartamentosControl(@RequestBody @Valid Departamento departamento){
		Departamento salvaDepartamento = deparService.salvaDepartamento(departamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaDepartamento);
	}

	@PutMapping("/{id}")
	@Operation (summary = "Altera um Departamento")
	public ResponseEntity<Departamento> alteraDepartamentoControlId(@PathVariable Long id,@RequestBody @Valid Departamento departamento ){
		Departamento alteraDepartamento = deparService.alterarDepartamento(id, departamento);
		if(alteraDepartamento != null) {
			return ResponseEntity.ok(departamento);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Apaga um Departamento")
	public ResponseEntity<Departamento> apagaDepartamentoControl(@PathVariable Long id){
		boolean apagar = deparService.apagarDepartamento(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {	
			return ResponseEntity.notFound().build();
		}
	}
}
