package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface EstudanteController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna o estudante por id", tags = { ESTUDANTE })
	public ResponseEntity<EstudanteDTO> getEstudante(Long codEstudante);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna todos os estudantes", tags = { ESTUDANTE })
	public ResponseEntity<List<EstudanteDTO>> getEstudantes(EstudanteFiltroDTO filtro);

	@ApiResponse(responseCode = "201")
	@Operation(summary = "Cadastra um novo estudante", tags = { ESTUDANTE })
	public ResponseEntity<EstudanteDTO> postEstudante(EstudanteDTO dto);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna a lista de vagas recomendadas de acordo com o estudante", tags = { VAGA, ESTUDANTE })
	public ResponseEntity<List<VagaDTO>> getVagasRecomendadas(Long codEstudante, @ParameterObject Pageable paginacao);

	@ApiResponse(responseCode = "201")
	@Operation(summary = "Atualiza um estudante", tags = { ESTUDANTE })
	public ResponseEntity<EstudanteDTO> putEstudante(Long codEstudante, EstudanteDTO body);
}
