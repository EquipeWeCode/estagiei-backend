package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface EstudanteController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna o estudante por id", tags = { ESTUDANTE })
	public ResponseEntity<EstudanteDTO> getEstudante(Long codEstudante);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna todos os estudantes", tags = { ESTUDANTE })
	public ResponseEntity<List<EstudanteDTO>> getEstudantes(EstudanteFiltroDTO filtro);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Cadastra um novo estudante", tags = { ESTUDANTE })
	public ResponseEntity<EstudanteDTO> postEstudante(EstudanteDTO dto);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Atualiza um estudante", tags = { ESTUDANTE })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<EstudanteDTO> putEstudante(Long codEstudante, EstudanteDTO body);
}
