package br.edu.ifsp.estagiei.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.repository.StudentRepository;

@RestController
public class EstudanteController {
	
	@Autowired
	private StudentRepository repositorio;
	
	@GetMapping(path = "api/estudante/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Estudante> getEstudante(@PathVariable String id){
		Optional<Estudante> estudante = repositorio.findById(id);
		return ResponseEntity.ok(estudante.get());
	}
}
