package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.ContatoDTO;
import br.edu.ifsp.estagiei.dto.ContatoDTO.ContatoDTOBuilder;
import br.edu.ifsp.estagiei.entity.Contato;

@Component
public class ContatoDTOFactory {

	private AuditoriaDTOFactory auditoriaFactory;

	@Autowired
	public ContatoDTOFactory(@Lazy AuditoriaDTOFactory auditoriaFactory) {
		this.auditoriaFactory = auditoriaFactory;
	}

	public List<ContatoDTO> buildDTOs(Collection<Contato> contatos) {
		return contatos.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public ContatoDTO buildDTO(Contato entidade) {
		ContatoDTOBuilder builder = ContatoDTO.builder();

		return builder.codContato(entidade.getCodContato())
				.descContato(entidade.getDescContato())
				.tipContato(entidade.getTipContato())
				.valorContato(entidade.getValorContato())
				.auditoria(auditoriaFactory.buildDTO(entidade.getAuditoria()))
				.build();
	}

	public Set<Contato> buildEntities(Collection<ContatoDTO> dto) {
		return dto.stream().map(this::buildEntity).collect(Collectors.toSet());
	}

	public Contato buildEntity(ContatoDTO dto) {
		Contato entidade = new Contato();
		entidade.setCodContato(dto.getCodContato());
		entidade.setDescContato(dto.getDescContato());
		entidade.setTipContato(dto.getTipContato());
		entidade.setValorContato(dto.getValorContato());
		return entidade;
	}

}
