package br.edu.ifsp.estagiei.dto.factory;

import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EnderecoDTO;
import br.edu.ifsp.estagiei.entity.Endereco;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EnderecoDTOFactory {
	
	public Endereco buildEntity(EnderecoDTO dto) {
		Endereco entidade = new Endereco();
		entidade.setCodEndereco(dto.getCodEndereco());
		entidade.setBairro(dto.getBairro());
		entidade.setCep(dto.getCep());
		entidade.setCidade(dto.getCidade());
		entidade.setComplemento(dto.getComplemento());
		entidade.setEstado(dto.getEstado());
		entidade.setLogradouro(dto.getLogradouro());
		entidade.setNumero(dto.getNumero());
		entidade.setIndAtivo(dto.getIndAtivo());
		return entidade;
	}
	
}
