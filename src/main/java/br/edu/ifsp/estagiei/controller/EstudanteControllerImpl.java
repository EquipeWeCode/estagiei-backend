package br.edu.ifsp.estagiei.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import br.edu.ifsp.estagiei.service.EstudanteService;

@RestController
@RequestMapping(EstudanteControllerImpl.PATH)
public class EstudanteControllerImpl implements EstudanteController {

	public static final String PATH = "/estudante";
	private static final String PATH_ID = "/{codEstudante}";

	@Autowired
	private EstudanteService service;

	@GetMapping(PATH_ID)
	public ResponseEntity<EstudanteDTO> getEstudante(@PathVariable String codEstudante) {
		EstudanteDTO estudante = service.findEstudanteByCodEstudante(codEstudante);
		return ResponseEntity.ok(estudante);
	}
	
	@GetMapping()
	public ResponseEntity<List<EstudanteDTO>> getEstudantes(EstudanteFiltroDTO filtro) {
		List<EstudanteDTO> estudantes = service.buscaTodos(filtro);
		return ResponseEntity.ok(estudantes);
	}

	@GetMapping(PATH_ID + "/recomendacao")
	public ResponseEntity<List<VagaDTO>> getVagasRecomendadas(@PathVariable String codEstudante) {
		List<VagaDTO> vagas = service.buscaVagasRecomendadas(codEstudante);
		return ResponseEntity.ok(vagas);
	}

	@PutMapping(PATH_ID)
	public ResponseEntity<EstudanteDTO> putEstudante(@PathVariable String codEstudante,
			@RequestBody @Valid EstudanteDTO dto) {
		dto.setCodEstudante(codEstudante);
		EstudanteDTO estudante = service.salvaEstudante(dto);
		return ResponseEntity.ok(estudante);
	}
}
