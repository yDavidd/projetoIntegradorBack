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

import com.empresaqtx.entities.Empresa;
import com.empresaqtx.services.EmpService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Empresas", description = "API REST DE GERNECIAMENTO DE EMPRESAS")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/empresas") 
	public class EmpController {
	private final EmpService empService;

	@Autowired
	public EmpController(EmpService empService) {
		this.empService = empService;
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza empresa por ID")
	public ResponseEntity<Empresa> buscaEmpresaControlId(@PathVariable Long id){
		Empresa empresa = empService.buscaEmpresaId(id);
		if(empresa != null) {
			return ResponseEntity.ok(empresa);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todas as empresas")
	public ResponseEntity<List<Empresa>> buscaTodosEmpresaControl(){
		List<Empresa> Empresas = empService.buscaTodosEmpresas();
		return ResponseEntity.ok(Empresas);
	}

	@PostMapping
	@Operation (summary = "Cadastra uma empresa")
	public ResponseEntity<Empresa> salvaEmpresasControl(@RequestBody @Valid Empresa empresa){
		Empresa salvaEmpresa= empService.salvaEmpresa(empresa);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaEmpresa);
	}

	@PutMapping("/{id}")
	@Operation (summary = "Altera uma empresa")
	public ResponseEntity<Empresa> alteraEmpresaControlId(@PathVariable Long id,@RequestBody @Valid Empresa empresa ){
		Empresa alteraEmpresa = empService.alterarEmpresa(id, empresa);
		if(alteraEmpresa != null) {
			return ResponseEntity.ok(empresa);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Apaga uma empresa")
	public ResponseEntity<Empresa> apagaEmpresaControl(@PathVariable Long id){
		boolean apagar = empService.apagarEmpresa(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {	
			return ResponseEntity.notFound().build();
		}
	}
}
