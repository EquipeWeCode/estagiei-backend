package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.VagaFiltroDTO;
import br.edu.ifsp.estagiei.service.VagaService;

@RestController
@RequestMapping(VagaControllerImpl.PATH)
public class VagaControllerImpl implements VagaController {

	public static final String PATH = "/vaga";

	@Autowired
	private VagaService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<VagaDTO>> getVagas(VagaFiltroDTO filtro) {
		List<VagaDTO> vagas = service.buscaTodos(filtro);
		return ResponseEntity.ok(vagas);
	}
}
