package br.edu.ifsp.estagiei.controller;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.filter.EmpresaFiltroDTO;
import br.edu.ifsp.estagiei.service.EmpresaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(EmpresaControllerImpl.PATH)
public class EmpresaControllerImpl implements EmpresaController {

	public static final String PATH = "/empresa";
	public static final String PATH_ID = "{codEmpresa}";

	@Autowired
	private EmpresaService service;

	@GetMapping
	public ResponseEntity<List<EmpresaDTO>> getEmpresas(
			@Parameter(in = ParameterIn.QUERY, required = false, allowEmptyValue = true) EmpresaFiltroDTO filtro,
			@ParameterObject Pageable paginacao) {
		List<EmpresaDTO> empresas = service.buscaEmpresas(filtro, paginacao);
		return respostaPaginada(filtro.getQuantidadeTotal()).body(empresas);
	}

	@GetMapping(PATH_ID)
	public ResponseEntity<EmpresaDTO> getEmpresa(@PathVariable(P_COD_EMPRESA) Long codEmpresa) {
		EmpresaDTO empresa = service.buscaEmpresa(codEmpresa);
		return ResponseEntity.ok(empresa);
	}

	@PostMapping
	public ResponseEntity<EmpresaDTO> postEmpresa(@RequestBody @Valid EmpresaDTO dto) {
		EmpresaDTO empresa = service.salvaEmpresa(dto, false);
		return ResponseEntity.ok(empresa);
	}
}
