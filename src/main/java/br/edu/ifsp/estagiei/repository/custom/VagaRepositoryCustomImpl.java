package br.edu.ifsp.estagiei.repository.custom;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Auditoria_;
import br.edu.ifsp.estagiei.entity.Candidatura_;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Competencia_;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Empresa_;
import br.edu.ifsp.estagiei.entity.Endereco;
import br.edu.ifsp.estagiei.entity.Endereco_;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Estudante_;
import br.edu.ifsp.estagiei.entity.Usuario_;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.entity.Vaga_;
import br.edu.ifsp.estagiei.repository.RepositoryImpl;

@Repository
@SuppressWarnings("unchecked")
public class VagaRepositoryCustomImpl extends RepositoryImpl implements VagaRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public Vaga buscaVagaPorId(Long codVaga) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);

		r.fetch(Vaga_.competencias, JoinType.LEFT);
		r.fetch(Vaga_.candidaturas, JoinType.LEFT).fetch(Candidatura_.estudante, JoinType.LEFT);
		r.fetch(Vaga_.endereco, JoinType.LEFT);
		FetchParent<Vaga, Empresa> fetchEmpresa = r.fetch(Vaga_.empresa, JoinType.INNER);
		fetchEmpresa.fetch(Empresa_.usuario);
		fetchEmpresa.fetch(Empresa_.endereco, JoinType.LEFT);

		criteria.distinct(true).select(r).where(cb.equal(r.get(Vaga_.codVaga), codVaga));

		return em.createQuery(criteria).getSingleResult();
	}

	public List<Long> buscaVagasRecomendadas(Long codEstudante) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);

		Join<Vaga, Competencia> joinCompetencias = r.join(Vaga_.competencias, JoinType.LEFT);
		Join<Competencia, Estudante> joinEstudantes = joinCompetencias.join(Competencia_.estudantes, JoinType.LEFT);

		criteria.distinct(true).select(r).where(cb.equal(joinEstudantes.get(Estudante_.codEstudante), codEstudante));

		criteria.groupBy(r.get(Vaga_.codVaga));
		criteria.orderBy(cb.asc(r.get(Vaga_.titulo)));

		List<Vaga> vagasRecomendadas = em.createQuery(criteria).getResultList();
		List<Long> ids = Lists.newArrayList();

		for (Vaga vaga : vagasRecomendadas) {
			ids.add(vaga.getCodVaga());
		}

		return ids;
	}

	public Page<Vaga> buscaTodosPorFiltro(VagaFiltroDTO filtro, Pageable paginacao) {
		VagaFiltroDTO novoFiltro = Optional.ofNullable(filtro).orElse(new VagaFiltroDTO());
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);

		r.fetch(Vaga_.endereco, JoinType.LEFT);
		r.fetch(Vaga_.competencias, JoinType.LEFT);
		FetchParent<Vaga, Empresa> fetchEmpresa = r.fetch(Vaga_.empresa, JoinType.INNER);
		fetchEmpresa.fetch(Empresa_.usuario).fetch(Usuario_.permissoes);
		fetchEmpresa.fetch(Empresa_.endereco, JoinType.LEFT);

		Path<LocalDateTime> dataInclusao = r.get(Vaga_.auditoria).get(Auditoria_.dataInclusao);

		criteria.distinct(true).select(r).where(aplicaFiltros(r, novoFiltro)).orderBy(cb.desc(dataInclusao));

		return (Page<Vaga>) geraPaginacao(paginacao, em.createQuery(criteria).getResultList(), filtro);
	}

	private Predicate[] aplicaFiltros(Root<Vaga> root, VagaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Predicate> predicates = Lists.newArrayList();
		Join<Vaga, Empresa> empresa = root.join(Vaga_.empresa);

		if (filtro.hasDescricao()) {
			predicates.add(cb.like(cb.upper(root.get(Vaga_.descricao)), filtro.getDescricaoFiltro()));
		}
		if (filtro.hasTitulo()) {
			predicates.add(cb.like(cb.upper(root.get(Vaga_.titulo)), filtro.getTituloFiltro()));
		}
		if (filtro.hasCurso()) {
			predicates.add(cb.like(cb.upper(root.get(Vaga_.curso)), filtro.getCursoFiltro()));
		}
		if (filtro.hasModalidade()) {
			predicates.add(cb.equal(root.get(Vaga_.modalidade), filtro.getModalidade()));
		}
		if (filtro.hasIds()) {
			predicates.add(root.get(Vaga_.codVaga).in(filtro.getIds()));
		}
		if (filtro.hasCodEmpresa()) {
			predicates.add(cb.equal(empresa.get(Empresa_.codEmpresa), filtro.getCodEmpresa()));
		}
		if (filtro.hasNomeEmpresa()) {
			predicates.add(cb.equal(cb.upper(empresa.get(Empresa_.nomeFantasia)), filtro.getNomeEmpresaFiltro()));
		}
		if (filtro.hasCodEstudante()) {
			predicates.add(root.get(Vaga_.codVaga).in(buscaVagasRecomendadas(filtro.getCodEstudante())));
		}
		if (filtro.isAtivo()) {
			predicates.add(cb.isTrue(root.get(Vaga_.indAtivo)));
		} else if (filtro.isNotAtivo()) {
			predicates.add(cb.isFalse(root.get(Vaga_.indAtivo)));
		}

		aplicaFiltrosEndereco(filtro, cb, predicates, root);

		return predicates.stream().toArray(Predicate[]::new);
	}

	private void aplicaFiltrosEndereco(VagaFiltroDTO filtro, CriteriaBuilder cb, List<Predicate> predicates,
			Root<Vaga> root) {
		Join<Vaga, Endereco> endereco = root.join(Vaga_.endereco, JoinType.LEFT);
		if (filtro.hasCep()) {
			predicates.add(cb.equal(endereco.get(Endereco_.cep), filtro.getCep()));
		}
		if (filtro.hasBairro()) {
			predicates.add(cb.like(cb.upper(endereco.get(Endereco_.bairro)), filtro.getBairroFiltro()));
		}
		if (filtro.hasCidade()) {
			predicates.add(cb.like(cb.upper(endereco.get(Endereco_.cidade)), filtro.getCidadeFiltro()));
		}
		if (filtro.hasEstado()) {
			predicates.add(cb.like(cb.upper(endereco.get(Endereco_.estado)), filtro.getEstadoFiltro()));
		}
	}
}
