package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.service.VagaService;

@RestController
public class VagaControllerImpl implements VagaController {

	@Autowired
	private VagaService service;

	@GetMapping("/vaga")
	@ResponseBody
	public ResponseEntity<List<VagaDTO>> getVagas() {
		List<VagaDTO> vagas = service.findAll();
		return ResponseEntity.ok(vagas);
	}
}
