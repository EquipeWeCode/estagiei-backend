package br.edu.ifsp.estagiei.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface EstudanteController extends Controller {
	@ApiResponse(responseCode =  "200")
	@Operation(summary = "Retorna o estudante por id", tags = {ESTUDANTE}, method = "GET")
	public ResponseEntity<EstudanteDTO> getEstudante(@PathVariable String id);
}
