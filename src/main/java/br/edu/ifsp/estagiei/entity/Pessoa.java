package br.edu.ifsp.estagiei.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class Pessoa {
	@Id
	@SequenceGenerator(name = "tb_pessoa_cod_pessoa_seq", sequenceName = "tb_pessoa_cod_pessoa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_pessoa_cod_pessoa_seq")
	@Column(name = "cod_pessoa", updatable = false)
	private Long codPessoa;
	@Column(name = "dt_nasc", columnDefinition = "DATE")
	private LocalDate dataNascimento;
	@Column(name = "rg")
	private String rg;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "nome")
	private String nome;

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "cod_usuario")
	@JsonIgnore
	private Usuario usuario;

	@OneToOne(mappedBy = "pessoa", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JsonIgnore
	private Estudante estudante;

	@Override
	public int hashCode() {
		return Objects.hash(codPessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(codPessoa, other.codPessoa);
	}
}
