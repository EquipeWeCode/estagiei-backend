package br.edu.ifsp.estagiei.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.service.EmpresaService;

@RestController
@RequestMapping(EmpresaControllerImpl.PATH)
public class EmpresaControllerImpl implements EmpresaController {
	
	public static final String PATH = "/empresa";
	public static final String PATH_ID = "{codEmpresa}";

	@Autowired
	private EmpresaService service;

	@GetMapping
	public ResponseEntity<List<EmpresaDTO>> getEmpresas() {
		List<EmpresaDTO> empresas = service.buscaEmpresas();
		return ResponseEntity.ok(empresas); 
	}
	
	@GetMapping(PATH_ID)
	public ResponseEntity<EmpresaDTO> getEmpresa(@PathVariable(P_COD_EMPRESA) String codEmpresa) {
		EmpresaDTO empresa = service.buscaEmpresa(Long.valueOf(codEmpresa));
		return ResponseEntity.ok(empresa);
	}
	
	@PostMapping
	@RolesAllowed({ROLE_ADMIN, ROLE_EMPRESA})
	public ResponseEntity<EmpresaDTO> postEmpresa(@RequestBody @Valid EmpresaDTO dto) {
		EmpresaDTO empresa = service.salvaEmpresa(dto);
		return ResponseEntity.ok(empresa);
	}
}
