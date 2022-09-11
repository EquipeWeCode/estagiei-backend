package br.edu.ifsp.estagiei.dto.factory;

import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.AuditoriaDTO;
import br.edu.ifsp.estagiei.entity.Auditoria;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class AuditoriaDTOFactory {

	public AuditoriaDTO buildDTO(Auditoria entidade) {
		if(entidade == null) {
			return null;
		}
		
		return AuditoriaDTO.builder().dataInclusao(entidade.getDataInclusao())
				.dataAlteracao(entidade.getDataAlteracao()).build();
	}
}
