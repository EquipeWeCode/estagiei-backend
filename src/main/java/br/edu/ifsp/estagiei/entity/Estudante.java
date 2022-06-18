package br.edu.ifsp.estagiei.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
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

	@ManyToMany
	@JoinTable(name = "tb_comp_estud", joinColumns = @JoinColumn(name = "cod_estudante"), inverseJoinColumns = @JoinColumn(name = "cod_competencia"))
	private Set<Competencia> competencias = new HashSet<>();

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "cod_pessoa")
	@JsonIgnore
	private Pessoa pessoa;

	public Boolean hasCompetencias() {
		return Persistence.getPersistenceUtil().isLoaded(this, "competencias");
	}

	public Boolean hasPessoa() {
		return Persistence.getPersistenceUtil().isLoaded(this, "pessoa");
	}

	public Competencia novaCompetencia(Long codCompetencia) {
		Competencia novaCompetencia = new Competencia();
		novaCompetencia.setCodCompetencia(codCompetencia);

		Competencia competencia = competencias.stream().filter(c -> c.equals(novaCompetencia)).findFirst()
				.orElse(novaCompetencia);
		competencias.add(competencia);
		return competencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEstudante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Estudante))
			return false;
		Estudante other = (Estudante) obj;
		return Objects.equals(codEstudante, other.codEstudante);
	}
}
