package com.empresaqtx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresaqtx.entities.Cliente;
import com.empresaqtx.entities.Departamento;
import com.empresaqtx.entities.Empresa;
import com.empresaqtx.entities.Fornecedor;
import com.empresaqtx.entities.Funcionario;
import com.empresaqtx.entities.Projeto;
import com.empresaqtx.services.ClieService;
import com.empresaqtx.services.DeparService;
import com.empresaqtx.services.EmpService;
import com.empresaqtx.services.FornService;
import com.empresaqtx.services.FunService;
import com.empresaqtx.services.ProjService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/ApiExterna")
public class ApiExternaController {
	
	private final ClieService clieService;
	private final EmpService empService;
	private final FornService fornService;
	private final FunService funService;
	private final DeparService deparService;
	private final ProjService projService;
	
	@Autowired
	public ApiExternaController(ProjService projService, ClieService clieService , EmpService empService,FornService fornService, FunService funService,DeparService deparService) {
		this.projService = projService;
		this.clieService = clieService;
		this.empService = empService;
		this.fornService = fornService;
		this.funService = funService;
		this.deparService = deparService;
		
	}
	
	@GetMapping("/clientes")
	@Operation(summary = "Apresenta todos os clientes")
	public ResponseEntity<List<Cliente>> buscaTodosClienteCOntrol(){
		List<Cliente> Cliente = clieService.buscaTodosCliente();
		return ResponseEntity.ok(Cliente);
	}
	@GetMapping("/empresas")
	@Operation(summary = "Apresenta todos as Empresas")
	public ResponseEntity<List<Empresa>> buscaTodosEmpresasControl(){
		List<Empresa> Empresa = empService.buscaTodosEmpresas();
		return ResponseEntity.ok(Empresa);
	}
	@GetMapping("/fornecedores")
	@Operation(summary = "Apresenta todos os fornecedores")
	public ResponseEntity<List<Fornecedor>> buscaTodosFornecedorCOntrol(){
		List<Fornecedor> Fornecedor = fornService.buscaTodosFornecedor();
		return ResponseEntity.ok(Fornecedor);
	}
	@GetMapping("/funcionarios")
	@Operation(summary = "Apresenta todos os Funcionarios")
	public ResponseEntity<List<Funcionario>> buscaTodosFuncionarioControl(){
		List<Funcionario> Funcionario = funService.buscaTodosFuncionarios();
		return ResponseEntity.ok(Funcionario);
	}
	@GetMapping("/departamentos")
	@Operation(summary = "Apresenta todos os Departamentos")
	public ResponseEntity<List<Departamento>> buscaTodosDepartamentoControl(){
		List<Departamento> Departamento = deparService.buscaTodosDepartamentos();
		return ResponseEntity.ok(Departamento);
	}
	@GetMapping("/projetos")
	@Operation(summary = "Apresenta todos os Projetos")
	public ResponseEntity<List<Projeto>> buscaTodosProjetosControl(){
		List<Projeto> Projeto = projService.buscaTodosProjetos();
		return ResponseEntity.ok(Projeto);
	}

}
