package br.edu.ifsp.estagiei.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	private Long codEstudante;
	@Column(name = "cod_pessoa", updatable = false, insertable = false)
	private Long codPessoa;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	@Embedded
	private Auditoria auditoria;

	@ManyToMany
	@JoinTable(name = "tb_comp_estud", joinColumns = @JoinColumn(name = "cod_estudante"), inverseJoinColumns = @JoinColumn(name = "cod_competencia"))
	private Set<Competencia> competencias = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "cod_pessoa")
	@JsonIgnore
	private Pessoa pessoa;

	@OneToMany(mappedBy = "estudante", fetch = FetchType.LAZY)
	private Set<ExperienciaProfissional> experienciaProfissional = new HashSet<>();

	@OneToMany(mappedBy = "estudante", fetch = FetchType.LAZY)
	private Set<HistoricoEscolar> historicoEscolar = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Vaga> vagas = new HashSet<>();

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

	public void retemCompetencias(List<Competencia> lista) {
		competencias.retainAll(lista);
	}

	public ExperienciaProfissional novaExperienciaProfissional(Long chavePrimaria) {
		ExperienciaProfissional novaEntidade = new ExperienciaProfissional();
		novaEntidade.setCodExpProfissional(chavePrimaria);

		ExperienciaProfissional entidade = experienciaProfissional.stream().filter(v -> v.equals(novaEntidade))
				.findFirst().orElse(novaEntidade);
		experienciaProfissional.add(entidade);
		return entidade;
	}

	public void retemExperiencias(List<ExperienciaProfissional> lista) {
		experienciaProfissional.retainAll(lista);
	}

	public HistoricoEscolar novoHistoricoEscolar(Long chavePrimaria) {
		HistoricoEscolar novaEntidade = new HistoricoEscolar();
		novaEntidade.setCodHistEscolar(chavePrimaria);

		HistoricoEscolar entidade = historicoEscolar.stream().filter(v -> v.equals(novaEntidade)).findFirst()
				.orElse(novaEntidade);
		historicoEscolar.add(entidade);
		return entidade;
	}

	public void retemHistoricos(List<HistoricoEscolar> lista) {
		historicoEscolar.retainAll(lista);
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
