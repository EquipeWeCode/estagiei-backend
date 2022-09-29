package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Candidatura_;

@Repository
public class CandidaturaRepositoryCustomImpl implements CandidaturaRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	public Candidatura findByIds(Long codEstudante, Long codVaga) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Candidatura> criteria = cb.createQuery(Candidatura.class);
		Root<Candidatura> r = criteria.from(Candidatura.class);

		r.fetch(Candidatura_.estudante);
		r.fetch(Candidatura_.vaga);

		List<Predicate> predicates = Lists.newArrayList();

		predicates.add(cb.equal(r.get(Candidatura_.codEstudante), codEstudante));
		predicates.add(cb.equal(r.get(Candidatura_.codVaga), codVaga));

		criteria.where(predicates.stream().toArray(Predicate[]::new));
		return em.createQuery(criteria).getSingleResult();
	}

	public Page<Candidatura> findCandidaturasByCodEstudante(Long codEstudante, Pageable paginacao) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Candidatura> criteria = cb.createQuery(Candidatura.class);
		Root<Candidatura> r = criteria.from(Candidatura.class);

		r.fetch(Candidatura_.estudante);
		r.fetch(Candidatura_.vaga);

		criteria.where(cb.equal(r.get(Candidatura_.codEstudante), codEstudante));
		return geraPaginacao(paginacao, em.createQuery(criteria).getResultList());
	}

	private Page<Candidatura> geraPaginacao(Pageable paginacao, List<Candidatura> candidaturas) {
		int inicio = (int) paginacao.getOffset();
		int fim = (int) ((inicio + paginacao.getPageSize()) > candidaturas.size() ? candidaturas.size()
				: (inicio + paginacao.getPageSize()));
		Page<Candidatura> pagina = new PageImpl<Candidatura>(candidaturas.subList(inicio, fim), paginacao,
				candidaturas.size());
		return pagina;
	}
}
