package br.edu.ifsp.estagiei.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.service.VagaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(VagaControllerImpl.PATH)
public class VagaControllerImpl implements VagaController {

	public static final String PATH = "/vaga";
	public static final String PATH_ID = "{codVaga}";

	@Autowired
	private VagaService service;

	@GetMapping
	public ResponseEntity<List<VagaDTO>> getVagas(
			@Parameter(in = ParameterIn.QUERY, required = false, allowEmptyValue = true) VagaFiltroDTO filtro,
			@ParameterObject Pageable paginacao) {
		List<VagaDTO> vagas = service.buscaTodos(filtro, paginacao);
		return respostaPaginada(filtro.getQuantidadeTotal()).body(vagas);
	}

	@GetMapping(PATH_ID)
	public ResponseEntity<VagaDTO> getVaga(@PathVariable(P_COD_VAGA) Long codVaga) {
		VagaDTO vaga = service.buscaVaga(codVaga);
		return ResponseEntity.ok(vaga);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('" + ROLE_EMPRESA + "')")
	public ResponseEntity<VagaDTO> postVaga(@RequestBody @Valid VagaDTO dto) {
		VagaDTO vaga = service.salvaVaga(dto, false);
		URI uriCreated = uriCreated(PATH + "/" + PATH_ID, vaga.getCodVaga());
		return ResponseEntity.created(uriCreated).body(vaga);
	}

	@PutMapping(PATH_ID)
	@PreAuthorize("hasAuthority('" + ROLE_EMPRESA + "')")
	public ResponseEntity<VagaDTO> putvaga(@PathVariable(P_COD_VAGA) Long codVaga, @RequestBody @Valid VagaDTO dto) {
		dto.setCodVaga(codVaga);
		VagaDTO vaga = service.salvaVaga(dto, true);
		return ResponseEntity.ok(vaga);
	}

}
