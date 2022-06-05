package br.edu.ifsp.estagiei.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_estudante")
@Getter
@Setter
@NoArgsConstructor
public class Estudante {
	@Id
	@Column(name = "cod_estudante", updatable = false)
	private String codEstudante;
	@Column(name = "inst_ensino")
	private String instEnsino;
	@Column(name = "nvl_ensino")
	private String nvlEnsino;
	@Column(name = "expProfissional")
	private String expProfissional;

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "cod_pessoa")
	@JsonIgnore
	private Pessoa pessoa;
}
