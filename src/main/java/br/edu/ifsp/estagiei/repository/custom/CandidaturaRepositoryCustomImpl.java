package br.edu.ifsp.estagiei.repository.custom;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.dto.filter.CandidaturaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Auditoria_;
import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Candidatura_;
import br.edu.ifsp.estagiei.entity.Empresa_;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Estudante_;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Pessoa_;
import br.edu.ifsp.estagiei.entity.Usuario_;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.entity.Vaga_;
import br.edu.ifsp.estagiei.repository.RepositoryImpl;

@Repository
public class CandidaturaRepositoryCustomImpl extends RepositoryImpl implements CandidaturaRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	public Candidatura findByIds(Long codEstudante, Long codVaga) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Candidatura> criteria = cb.createQuery(Candidatura.class);
			Root<Candidatura> r = criteria.from(Candidatura.class);

			Fetch<Candidatura, Estudante> fetchEstudante = r.fetch(Candidatura_.estudante, JoinType.LEFT);
			fetchEstudante.fetch(Estudante_.competencias, JoinType.LEFT);
			Fetch<Estudante, Pessoa> fetchPessoaEstudante = fetchEstudante.fetch(Estudante_.pessoa, JoinType.LEFT);
			fetchPessoaEstudante.fetch(Pessoa_.usuario, JoinType.LEFT).fetch(Usuario_.permissoes, JoinType.LEFT);
			r.fetch(Candidatura_.vaga, JoinType.LEFT).fetch(Vaga_.empresa, JoinType.LEFT)
					.fetch(Empresa_.usuario, JoinType.LEFT).fetch(Usuario_.permissoes, JoinType.LEFT);

			List<Predicate> predicates = Lists.newArrayList();

			predicates.add(cb.equal(r.get(Candidatura_.codEstudante), codEstudante));
			predicates.add(cb.equal(r.get(Candidatura_.codVaga), codVaga));

			criteria.distinct(true).where(predicates.stream().toArray(Predicate[]::new));
			return em.createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Page<Candidatura> findCandidaturas(CandidaturaFiltroDTO filtro, Pageable paginacao) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Candidatura> criteria = cb.createQuery(Candidatura.class);
		Root<Candidatura> r = criteria.from(Candidatura.class);

		Fetch<Candidatura, Estudante> fetchEstudante = r.fetch(Candidatura_.estudante, JoinType.LEFT);
		fetchEstudante.fetch(Estudante_.competencias, JoinType.LEFT);
		Fetch<Estudante, Pessoa> fetchPessoaEstudante = fetchEstudante.fetch(Estudante_.pessoa, JoinType.LEFT);
		fetchPessoaEstudante.fetch(Pessoa_.usuario, JoinType.LEFT).fetch(Usuario_.permissoes, JoinType.LEFT);
		r.fetch(Candidatura_.vaga, JoinType.LEFT).fetch(Vaga_.empresa, JoinType.LEFT)
				.fetch(Empresa_.usuario, JoinType.LEFT).fetch(Usuario_.permissoes, JoinType.LEFT);

		Path<LocalDateTime> dataInclusao = r.get(Candidatura_.auditoria).get(Auditoria_.dataInclusao);

		criteria.distinct(true).where(aplicaFiltros(r, filtro)).orderBy(cb.desc(dataInclusao));
		return (Page<Candidatura>) geraPaginacao(paginacao, em.createQuery(criteria).getResultList(), filtro);
	}

	private Predicate[] aplicaFiltros(Root<Candidatura> root, CandidaturaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Predicate> predicates = Lists.newArrayList();

		if (filtro.hasCodEstudante()) {
			predicates.add(cb.equal(root.get(Candidatura_.codEstudante), filtro.getCodEstudante()));
		}

		if (filtro.hasCodVaga()) {
			predicates.add(cb.equal(root.get(Candidatura_.codVaga), filtro.getCodVaga()));
		}

		if (filtro.hasCodEmpresa()) {
			Path<Vaga> vaga = root.get(Candidatura_.vaga);
			Path<Long> codEmpresa = vaga.get(Vaga_.codEmpresa);
			predicates.add(cb.equal(codEmpresa, filtro.getCodEmpresa()));
		}

		if (filtro.isAtivo()) {
			predicates.add(cb.notEqual(root.get(Candidatura_.status), CandidaturaEnum.CANCELADO));
		}

		if (filtro.isNotAtivo()) {
			predicates.add(cb.equal(root.get(Candidatura_.status), CandidaturaEnum.CANCELADO));
		}

		return predicates.stream().toArray(Predicate[]::new);
	}
}
