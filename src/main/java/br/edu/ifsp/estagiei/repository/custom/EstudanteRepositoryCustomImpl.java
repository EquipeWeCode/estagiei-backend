package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Estudante_;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Pessoa_;

@Repository
public class EstudanteRepositoryCustomImpl implements EstudanteRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	public Estudante findByCodEstudante(Long codUsuario) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Estudante> criteria = cb.createQuery(Estudante.class);
		Root<Estudante> r = criteria.from(Estudante.class);

		r.fetch(Estudante_.competencias, JoinType.LEFT);
		r.fetch(Estudante_.pessoa, JoinType.LEFT).fetch(Pessoa_.usuario, JoinType.LEFT);
		criteria.where(cb.equal(r.get(Estudante_.codEstudante), codUsuario));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Estudante> buscaTodosPorFiltro(EstudanteFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Estudante> criteria = cb.createQuery(Estudante.class);
		Root<Estudante> r = criteria.from(Estudante.class);

		r.fetch(Estudante_.competencias, JoinType.LEFT);
		FetchParent<Estudante, Pessoa> fetchEstudantePessoa = r.fetch(Estudante_.pessoa, JoinType.LEFT);
		fetchEstudantePessoa.fetch(Pessoa_.usuario, JoinType.LEFT);
		
		criteria.distinct(true).select(r).where(aplicaFiltros(r, filtro));

		criteria.orderBy(cb.desc(r.get(Estudante_.pessoa).get(Pessoa_.nome)));

		return em.createQuery(criteria).getResultList();
	}

	private Predicate[] aplicaFiltros(Root<Estudante> root, EstudanteFiltroDTO filtro) {
		// TODO: Implementar os filtros
//		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Predicate> predicates = Lists.newArrayList();
		return predicates.stream().toArray(Predicate[]::new);
	}
}
