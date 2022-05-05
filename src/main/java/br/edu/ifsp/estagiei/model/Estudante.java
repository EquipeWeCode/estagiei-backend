package br.edu.ifsp.estagiei.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_estudante")
public class Estudante {
	@Id
	@Column(name = "cod_estudante")
	private Integer codEstudante;
	@Column(name = "nome")
	private String nome;
	@Column(name = "dt_nasc")
	private Date dtNasc;
	@Column(name = "rg")
	private String rg;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "intituicao_ensino")
	private String instituicaoEnsino;
	@Column(name = "nivel_escolaridade")
	private String nivelEscolaridade;
	@Column(name = "experiencia_profissional")
	private String experienciaProfissional;

	public Integer getCodEstudante() {
		return codEstudante;
	}

	public void setCodEstudante(Integer codEstudante) {
		this.codEstudante = codEstudante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getInstituicaoEnsino() {
		return instituicaoEnsino;
	}

	public void setInstituicaoEnsino(String instituicaoEnsino) {
		this.instituicaoEnsino = instituicaoEnsino;
	}

	public String getNivelEscolaridade() {
		return nivelEscolaridade;
	}

	public void setNivelEscolaridade(String nivelEscolaridade) {
		this.nivelEscolaridade = nivelEscolaridade;
	}

	public String getExperienciaProfissional() {
		return experienciaProfissional;
	}

	public void setExperienciaProfissional(String experienciaProfissional) {
		this.experienciaProfissional = experienciaProfissional;
	}

}
