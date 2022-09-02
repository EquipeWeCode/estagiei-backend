package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.factory.CompetenciaDTOFactory;
import br.edu.ifsp.estagiei.repository.CompetenciaRepository;

@RestController
@RequestMapping(CompetenciaControllerImpl.PATH)
public class CompetenciaControllerImpl implements CompetenciaController {
	
	public static final String PATH = "/competencia";
	
	@Autowired
	private CompetenciaRepository repositorio;
	@Autowired
	private CompetenciaDTOFactory factory;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<CompetenciaDTO>> getCompetencias() {
		List<CompetenciaDTO> competenciasDTO = Lists.newArrayList();
		repositorio.findAll().forEach(c -> competenciasDTO.add(factory.buildDTO(c)));
		return ResponseEntity.ok(competenciasDTO);
	}

}
