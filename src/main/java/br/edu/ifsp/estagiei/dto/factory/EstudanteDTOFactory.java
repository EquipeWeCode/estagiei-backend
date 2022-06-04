package br.edu.ifsp.estagiei.dto.factory;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.builder.EstudanteDTOBuilder;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EstudanteDTOFactory {
	public EstudanteDTO buildEstudante(Estudante estudante) {
		
		LocalDate dataNasc = estudante.getPessoa().getDataNascimento();
		String dataFormatada = EstagieiUtils.dataNascimentoParaString(dataNasc);
		
		EstudanteDTOBuilder builder =  EstudanteDTOBuilder.newInstance()
				.codUsuario(estudante.getPessoa().getUsuario().getCodUsuario())
				.avatar(estudante.getPessoa().getUsuario().getAvatar())
				.email(estudante.getPessoa().getUsuario().getEmail())
				.cpf(estudante.getPessoa().getCpf())
				.rg(estudante.getPessoa().getRg())
				.nome(estudante.getPessoa().getNome())
				.instEnsino(estudante.getInstEnsino())
				.nvlEnsino(estudante.getNvlEnsino())
				.expProfissional(estudante.getExpProfissional())
				.dataNascimento(dataFormatada);
				
				return builder.build();
	}
}
