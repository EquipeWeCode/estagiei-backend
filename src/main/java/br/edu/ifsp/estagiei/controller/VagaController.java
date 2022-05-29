package br.edu.ifsp.estagiei.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@RestController
public class VagaController implements IController {

	@Autowired
	VagaRepository repositorio;

	@GetMapping(ROOT_API + "/vaga")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Vaga>> getVagas() {
		Iterable<Vaga> vagas = repositorio.findAll();
		List<Vaga> vagasLista = new ArrayList<>();
		vagas.forEach(v -> vagasLista.add(v));
		return ResponseEntity.ok(vagasLista);
	}
}
