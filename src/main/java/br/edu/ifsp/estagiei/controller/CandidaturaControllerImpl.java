package br.edu.ifsp.estagiei.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import br.edu.ifsp.estagiei.dto.filter.CandidaturaFiltroDTO;
import br.edu.ifsp.estagiei.service.CandidaturaService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(CandidaturaControllerImpl.PATH)
public class CandidaturaControllerImpl implements CandidaturaController {

	@Autowired
	private CandidaturaService service;

	public static final String PATH = "/candidatura";
	public static final String PATH_ID = "/{codEstudante}/{codVaga}";
	private static final String PATH_ID_ESTUDANTE = "/{codEstudante}";

	@GetMapping
	@PreAuthorize("hasAnyAuthority('" + ROLE_EMPRESA + "','" + ROLE_ADMIN + "')")
	public ResponseEntity<List<CandidaturaDTO>> getCandidaturasFiltro(CandidaturaFiltroDTO filtro,
			@ParameterObject Pageable paginacao) {
		List<CandidaturaDTO> candidaturas = service.buscaCandidaturas(filtro, paginacao);
		return respostaPaginada(filtro.getQuantidadeTotal()).body(candidaturas);
	}

	@GetMapping(PATH_ID_ESTUDANTE)
	@PreAuthorize("hasAnyAuthority('" + ROLE_EMPRESA + "','" + ROLE_ESTUDANTE + "','" + ROLE_ADMIN + "')")
	public ResponseEntity<List<CandidaturaDTO>> getCandidaturasEstudante(@PathVariable Long codEstudante,
			@ParameterObject Pageable paginacao,
			@Parameter(in = ParameterIn.QUERY, required = false, allowEmptyValue = true) @RequestParam(required = false) Boolean indAtivo,
			@RequestParam(required = false) CandidaturaEnum status) {
		CandidaturaFiltroDTO filtro = new CandidaturaFiltroDTO();
		filtro.setCodEstudante(codEstudante);
		filtro.setIndAtivo(indAtivo);
		filtro.setStatus(status);
		List<CandidaturaDTO> candidaturas = service.findCandidaturasByCodEstudante(filtro, paginacao);
		return respostaPaginada(filtro.getQuantidadeTotal()).body(candidaturas);
	}

	@Override
	@PostMapping(PATH_ID)
	@PreAuthorize("hasAnyAuthority('" + ROLE_EMPRESA + "','" + ROLE_ESTUDANTE + "','" + ROLE_ADMIN + "')")
	public ResponseEntity<CandidaturaDTO> postCandidatura(@PathVariable Long codEstudante, @PathVariable Long codVaga) {
		CandidaturaDTO dto = new CandidaturaDTO();
		dto.setCodEstudante(codEstudante);
		dto.setCodVaga(codVaga);
		dto.setStatus(CandidaturaEnum.CANDIDATADO);

		CandidaturaDTO candidatura = service.salvaCandidatura(dto, false);

		URI uriCreated = URICreated(PATH + "/" + PATH_ID, candidatura.getCodEstudante(), candidatura.getCodVaga());
		return ResponseEntity.created(uriCreated).body(candidatura);
	}

	@Override
	@PutMapping
	@PreAuthorize("hasAnyAuthority('" + ROLE_EMPRESA + "','" + ROLE_ESTUDANTE + "','" + ROLE_ADMIN + "')")
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<CandidaturaDTO> putCandidatura(@RequestBody @Valid CandidaturaDTO dto) {
		CandidaturaDTO candidatura = service.salvaCandidatura(dto, true);
		return ResponseEntity.ok(candidatura);
	}

	@Override
	@DeleteMapping(PATH_ID)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCandidatura(@PathVariable Long codEstudante, @PathVariable Long codVaga) {
		service.excluiCandidatura(codEstudante, codVaga);
	}

}
