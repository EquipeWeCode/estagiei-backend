package br.edu.ifsp.estagiei.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.service.EstudanteService;
import br.edu.ifsp.estagiei.utils.ValidacaoException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EstudanteController implements IController {

	@Autowired
	private EstudanteService service;

	@GetMapping(path = ROOT_API + "/estudante/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Estudante> getEstudante(@PathVariable String id) throws ValidacaoException {
		System.out.println(id);
		Optional<Estudante> estudante = service.findEstudante(id);
		return ResponseEntity.ok(estudante.get());
	}
}
