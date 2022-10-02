package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import br.edu.ifsp.estagiei.service.CandidaturaService;

@RestController
@RequestMapping(CandidaturaControllerImpl.PATH)
public class CandidaturaControllerImpl implements CandidaturaController {

	@Autowired
	private CandidaturaService service;

	public static final String PATH = "/candidatura";
	public static final String PATH_ID = "/{codEstudante}/{codVaga}";
	private static final String PATH_ID_ESTUDANTE = "/{codEstudante}";

	@GetMapping(PATH_ID_ESTUDANTE)
	public ResponseEntity<List<CandidaturaDTO>> getCandidaturasEstudante(@PathVariable Long codEstudante,
			@ParameterObject Pageable paginacao) {
		List<CandidaturaDTO> candidaturas = service.findCandidaturasByCodEstudante(codEstudante, paginacao);
		return ResponseEntity.ok(candidaturas);
	}

	@Override
	@PostMapping(PATH_ID)
	public ResponseEntity<CandidaturaDTO> postCandidatura(@PathVariable Long codEstudante, @PathVariable Long codVaga) {
		CandidaturaDTO dto = new CandidaturaDTO();
		dto.setCodEstudante(codEstudante);
		dto.setCodVaga(codVaga);
		dto.setStatus(CandidaturaEnum.CANDIDATADO);
		
		CandidaturaDTO candidatura = service.salvaCandidatura(dto, false);
		return ResponseEntity.ok(candidatura);
	}

	@Override
	@PutMapping
	public ResponseEntity<CandidaturaDTO> putCandidatura(@RequestBody CandidaturaDTO dto) {
		CandidaturaDTO candidatura = service.salvaCandidatura(dto, true);
		return ResponseEntity.ok(candidatura);
	}

}
