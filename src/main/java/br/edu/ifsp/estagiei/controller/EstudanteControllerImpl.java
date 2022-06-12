package br.edu.ifsp.estagiei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.service.EstudanteService;

@RestController
public class EstudanteControllerImpl implements EstudanteController {

	@Autowired
	private EstudanteService service;

	@GetMapping("/estudante/{id}")
	public ResponseEntity<EstudanteDTO> getEstudante(@PathVariable String id) {
		EstudanteDTO estudante = service.findEstudanteByCodEstudante(id);
		return ResponseEntity.ok(estudante);
	}
}
