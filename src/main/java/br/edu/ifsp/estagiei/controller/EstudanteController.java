package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface EstudanteController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna o estudante por id", tags = { ESTUDANTE })
	public ResponseEntity<EstudanteDTO> getEstudante(@PathVariable String id);
	
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna a lista de vagas recomendadas de acordo com o estudante", tags = { VAGA, ESTUDANTE })
	public ResponseEntity<List<VagaDTO>> getVagasRecomendadas(String codEstudante);
}
