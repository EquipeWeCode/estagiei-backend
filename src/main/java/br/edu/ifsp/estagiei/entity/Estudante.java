package br.edu.ifsp.estagiei.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.collect.Sets;

import br.edu.ifsp.estagiei.constants.NvlEscolaridadeEnum;
import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_estudante")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditoriaListener.class)
public class Estudante implements Auditavel {
	@Id
	@SequenceGenerator(name = "tb_estudante_cod_estudante_seq", sequenceName = "tb_estudante_cod_estudante_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_estudante_cod_estudante_seq")
	@Column(name = "cod_estudante", updatable = false)
	private Long codEstudante;
	@Column(name = "cod_pessoa", updatable = false, insertable = false)
	private Long codPessoa;
	@Column(name = "nvl_escolaridade")
	private NvlEscolaridadeEnum nvlEscolaridade;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	@Embedded
	private Auditoria auditoria;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tb_comp_estud", joinColumns = @JoinColumn(name = "cod_estudante"), inverseJoinColumns = @JoinColumn(name = "cod_competencia"))
	private Set<Competencia> competencias = Sets.newHashSet();

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "cod_pessoa", referencedColumnName = "cod_pessoa")
	private Pessoa pessoa;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true, mappedBy = "estudante")
	private Set<ExperienciaProfissional> experienciaProfissional = Sets.newHashSet();

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE }, orphanRemoval = true, mappedBy = "estudante")
	private Set<HistoricoEscolar> historicoEscolar = Sets.newHashSet();
	
	@OneToMany(mappedBy = "estudante", fetch = FetchType.LAZY)
    private Set<Candidatura> candidaturas;

	public Boolean hasCompetencias() {
		return Persistence.getPersistenceUtil().isLoaded(this, "competencias")
				&& EstagieiUtils.isListNotEmptyOrNull(competencias);
	}

	public Boolean hasPessoa() {
		return Persistence.getPersistenceUtil().isLoaded(this, "pessoa") && pessoa != null;
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
