package br.edu.ifsp.estagiei.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

import br.edu.ifsp.estagiei.constants.ModalidadeEnum;
import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_vaga")
@EntityListeners(AuditoriaListener.class)
public class Vaga implements Auditavel {

	@Id
	@SequenceGenerator(name = "tb_vaga_cod_vaga_seq", sequenceName = "tb_vaga_cod_vaga_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_vaga_cod_vaga_seq")
	@Column(name = "cod_vaga", updatable = false)
	private Long codVaga;
	@Column(name = "cod_empresa", insertable = false, updatable = false)
	private Long codEmpresa;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "salario")
	private BigDecimal salario;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "curso")
	private String curso;
	@Column(name = "carga_horaria")
	private Integer cargaHoraria;
	@Column(name = "modalidade")
	private ModalidadeEnum modalidade;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	@Embedded
	private Auditoria auditoria;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_comp_vaga", joinColumns = @JoinColumn(name = "cod_vaga"), inverseJoinColumns = @JoinColumn(name = "cod_competencia"))
	private Set<Competencia> competencias = Sets.newHashSet();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_empresa", nullable = false)
	private Empresa empresa;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "vaga", fetch = FetchType.LAZY)
	private Set<Candidatura> candidaturas;

	public Vaga(Empresa empresa) {
		this.empresa = empresa;
	}

	public Competencia novaCompetencia(Competencia novaCompetencia) {
		Competencia competencia = competencias.stream().filter(c -> c.equals(novaCompetencia)).findFirst()
				.orElse(novaCompetencia);
		competencias.add(competencia);
		return competencia;
	}

	public void retemCompetencias(List<Competencia> lista) {
		competencias.retainAll(lista);
	}

	public Boolean hasCompetencias() {
		return Persistence.getPersistenceUtil().isLoaded(this, "competencias") && competencias != null;
	}

	public Boolean hasEmpresa() {
		return Persistence.getPersistenceUtil().isLoaded(this, "empresa") && empresa != null;
	}

	public boolean hasEndereco() {
		return Persistence.getPersistenceUtil().isLoaded(this, "endereco") && endereco != null;
	}

	public boolean hasCandidaturas() {
		return Persistence.getPersistenceUtil().isLoaded(this, "candidaturas") && candidaturas != null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codVaga);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vaga))
			return false;
		Vaga other = (Vaga) obj;
		return Objects.equals(codVaga, other.codVaga);
	}

	private String getModalidadeNullSafe() {
		if (this.modalidade != null) {
			String modalidadeCapitalizada = modalidade.toString().substring(0, 1).toUpperCase()
					+ modalidade.toString().substring(1).toLowerCase();
			return modalidadeCapitalizada;
		}
		return "";
	}

	@Override
	public String toString() {
		return this.titulo + " (" + getModalidadeNullSafe() + ")";
	}

	public boolean isActive() {
		return this.indAtivo != null && indAtivo;
	}

}
