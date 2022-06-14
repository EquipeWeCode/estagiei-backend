package br.edu.ifsp.estagiei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.service.EmpresaService;

@RestController
public class EmpresaControllerImpl implements EmpresaController{
	
	@Autowired
	private EmpresaService service;

	@PostMapping("/empresa")
	public ResponseEntity<EmpresaDTO> postEmpresa(@RequestBody EmpresaDTO dto) {
		EmpresaDTO empresa = service.salvaEmpresa(dto);
		return ResponseEntity.ok(empresa);
	}
}
