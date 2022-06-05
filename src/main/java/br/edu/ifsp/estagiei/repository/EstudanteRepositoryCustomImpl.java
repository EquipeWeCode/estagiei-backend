package br.edu.ifsp.estagiei.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.edu.ifsp.estagiei.entity.Estudante;

@Repository
public class EstudanteRepositoryCustomImpl implements EstudanteRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	public Estudante buscaPorCodEstudante(String codUsuario) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Estudante> criteria = cb.createQuery(Estudante.class);
		Root<Estudante> r = criteria.from(Estudante.class);

		r.fetch("pessoa", JoinType.LEFT).fetch("usuario", JoinType.LEFT);
		criteria.where(cb.equal(r.get("codEstudante"), codUsuario));
		return em.createQuery(criteria).getSingleResult();
	}
}
