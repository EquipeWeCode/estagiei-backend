package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
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
	
	@GetMapping(PATH_ID + "/recomendacao")
	public ResponseEntity<List<VagaDTO>> getVagasRecomendadas(@PathVariable String codEstudante) {
		List<VagaDTO> vagas = service.buscaVagasRecomendadas(codEstudante);
		return ResponseEntity.ok(vagas);
	}
}
