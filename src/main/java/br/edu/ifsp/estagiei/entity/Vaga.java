package br.edu.ifsp.estagiei.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_vaga")
@Getter
@Setter
@NoArgsConstructor
public class Vaga {
	@Id
	@Column(name = "cod_vaga")
	private Integer codVaga;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "salario")
	private BigDecimal salario;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "ind_ativo")
	private Boolean indAtivo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cod_empresa", nullable=false)
	private Empresa empresa;
}
