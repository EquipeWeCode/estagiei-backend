package br.edu.ifsp.estagiei.dto.factory;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EnderecoDTO;
import br.edu.ifsp.estagiei.dto.EnderecoDTO.EnderecoDTOBuilder;
import br.edu.ifsp.estagiei.entity.Endereco;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EnderecoDTOFactory {

	public Endereco buildEntity(EnderecoDTO dto) {
		if(dto == null) {
			return null;
		}

		String cepNumeros = Optional.ofNullable(dto.getCep().replaceAll("\\D+", "")).orElse(null);
		
		Endereco entidade = new Endereco();
		entidade.setCodEndereco(dto.getCodEndereco());
		entidade.setBairro(dto.getBairro());
		entidade.setCep(cepNumeros);
		entidade.setCidade(dto.getCidade());
		entidade.setComplemento(dto.getComplemento());
		entidade.setEstado(dto.getEstado());
		entidade.setLogradouro(dto.getLogradouro());
		entidade.setNumero(dto.getNumero());
		entidade.setIndAtivo(dto.getIndAtivo());
		return entidade;
	}

	public EnderecoDTO buildDTO(Endereco endereco) {
		if(endereco == null) {
			return null;
		}
		EnderecoDTOBuilder builder = EnderecoDTO.builder().codEndereco(endereco.getCodEndereco())
				.bairro(endereco.getBairro()).cep(endereco.getCep()).cidade(endereco.getCidade())
				.complemento(endereco.getComplemento()).logradouro(endereco.getLogradouro())
				.pontoReferencia(endereco.getPontoReferencia()).numero(endereco.getNumero());

		return builder.build();
	}

}
