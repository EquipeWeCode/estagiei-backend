package br.edu.ifsp.estagiei.entity;

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

@Entity
@Table(name = "tb_estudante")
@Getter
@Setter
@NoArgsConstructor
public class Estudante {
	@Id
	@SequenceGenerator(name = "tb_estudante_cod_estudante_seq", sequenceName = "tb_estudante_cod_estudante_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_estudante_cod_estudante_seq")
	@Column(name = "cod_estudante", updatable = false)
	private Long codEstudante;
	@Column(name = "inst_ensino")
	private String instEnsino;
	@Column(name = "nvl_ensino")
	private String nvlEnsino;
	@Column(name = "expProfissional")
	private String expProfissional;
	@Column(name = "ind_ativo")
	private Boolean indAtivo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_pessoa")
	@JsonIgnore
	private Pessoa pessoa;
}
