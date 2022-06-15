package br.edu.ifsp.estagiei.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
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
	@SequenceGenerator(name = "tb_vaga_cod_vaga_seq", sequenceName = "tb_vaga_cod_vaga_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_vaga_cod_vaga_seq")
	@Column(name = "cod_vaga", updatable = false)
	private Long codVaga;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "salario")
	private BigDecimal salario;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "tb_comp_vaga",
		joinColumns = @JoinColumn(name="cod_vaga"),
		inverseJoinColumns = @JoinColumn(name="cod_competencia"))
	private Set<Competencia> competencias = new HashSet<>();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_empresa", nullable = false)
	private Empresa empresa;
	
	public Boolean hasCompetencias() {
		return Persistence.getPersistenceUtil().isLoaded(this,"competencias");
	}
}
