package br.edu.ifsp.estagiei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.service.EstudanteService;

@RestController
public class EstudanteController implements IController {

	@Autowired
	private EstudanteService service;

	@GetMapping(path = ROOT_API + "/estudante/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<EstudanteDTO> getEstudante(@PathVariable String id) throws ValidacaoException {
		EstudanteDTO estudante = service.findEstudanteByCodUsuario(id);
		return ResponseEntity.ok(estudante);
	}
}
