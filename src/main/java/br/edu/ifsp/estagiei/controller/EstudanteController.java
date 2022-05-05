package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.model.Estudante;
import br.edu.ifsp.estagiei.repositorio.EstudanteRepositorio;

@RestController
public class EstudanteController {
	
	@Autowired
	EstudanteRepositorio dao;
	
	@GetMapping(path = "/estudante")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Estudante>> listarEstudantes(){
		List<Estudante> estudantes = dao.listarEstudantes(); 
		return ResponseEntity.ok(estudantes);
	}
}
