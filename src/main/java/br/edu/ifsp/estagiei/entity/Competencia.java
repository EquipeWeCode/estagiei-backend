package br.edu.ifsp.estagiei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_competencia")
@Getter
@Setter
@NoArgsConstructor
public class Competencia {
	@Id
	@Column(name = "cod_competencia")
	private Long codCompetencia;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
}
