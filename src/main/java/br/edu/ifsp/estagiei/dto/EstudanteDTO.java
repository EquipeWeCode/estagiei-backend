package br.edu.ifsp.estagiei.dto;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EstudanteDTO implements DTOUtils {
	
	private String codEstudante;
	private Long codUsuario;
	private String email;
	private String avatar;
	@CPF(message = "CPF Inválido")
	private String cpf;
	private String rg;
	private String nome;
	private String dataNascimento;
	private String instEnsino;
	private String nvlEnsino;
	private String expProfissional;
	private String contato;
	
	private List<CompetenciaDTO> competencias;

	public boolean hasDataNascimento() {
		return dataNascimento != null && !dataNascimento.isEmpty();
	}
	
	public boolean hasNome() {
		return nome != null && !nome.isEmpty();
	}
	
	public boolean hasCpf() {
		return cpf != null && !cpf.isEmpty();
	}
	
	public boolean hasCompetencias() {
		return getCompetencias() != null && getCompetencias().size()>0;
	}
}
