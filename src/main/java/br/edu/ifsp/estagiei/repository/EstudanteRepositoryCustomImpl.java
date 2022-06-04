package br.edu.ifsp.estagiei.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.stereotype.Repository;

import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;

@Repository
public class EstudanteRepositoryCustomImpl implements EstudanteRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	public Estudante buscaPorCodUsuario(String codUsuario) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Estudante> criteria = cb.createQuery(Estudante.class);
		Root<Estudante> r = criteria.from(Estudante.class);

		r.fetch("pessoa", JoinType.LEFT).fetch("usuario", JoinType.LEFT);
		criteria.where(cb.equal(r.get("pessoa").get("usuario").get("codUsuario"), codUsuario));
		return em.createQuery(criteria).getSingleResult();
	}
}
