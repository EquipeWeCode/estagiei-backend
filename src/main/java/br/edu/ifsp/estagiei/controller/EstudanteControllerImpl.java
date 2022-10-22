package br.edu.ifsp.estagiei.controller;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import br.edu.ifsp.estagiei.service.EstudanteService;

@RestController
@RequestMapping(EstudanteControllerImpl.PATH)
public class EstudanteControllerImpl implements EstudanteController {

	@Autowired
	private EstudanteService service;

	public static final String PATH = "/estudante";
	private static final String PATH_ID = "/{codEstudante}";

	@GetMapping(PATH_ID)
	public ResponseEntity<EstudanteDTO> getEstudante(@PathVariable Long codEstudante) {
		EstudanteDTO estudante = service.findEstudanteByCodEstudante(codEstudante);
		return ResponseEntity.ok(estudante);
	}

	@GetMapping
	public ResponseEntity<List<EstudanteDTO>> getEstudantes(@ParameterObject EstudanteFiltroDTO filtro) {
		List<EstudanteDTO> estudantes = service.buscaTodos(filtro);
		return ResponseEntity.ok(estudantes);
	}

	@Override
	@PostMapping
	public ResponseEntity<EstudanteDTO> postEstudante(@RequestBody @Valid EstudanteDTO dto) {
		EstudanteDTO estudante = service.salvaEstudante(dto, false);
		return ResponseEntity.ok(estudante);

	}

	@PutMapping(PATH_ID)
	@PreAuthorize("hasAnyAuthority('" + ROLE_ADMIN + "','" + ROLE_ESTUDANTE + "')")
	public ResponseEntity<EstudanteDTO> putEstudante(@PathVariable Long codEstudante,
			@RequestBody @Valid EstudanteDTO dto) {
		dto.setCodEstudante(codEstudante);
		EstudanteDTO estudante = service.salvaEstudante(dto, true);
		return ResponseEntity.ok(estudante);
	}
}
