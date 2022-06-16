package br.edu.ifsp.estagiei.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Estudante_;
import br.edu.ifsp.estagiei.entity.Pessoa_;

@Repository
public class EstudanteRepositoryCustomImpl implements EstudanteRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	public Estudante findByCodEstudante(String codUsuario) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Estudante> criteria = cb.createQuery(Estudante.class);
		Root<Estudante> r = criteria.from(Estudante.class);
		
		r.fetch(Estudante_.competencias, JoinType.LEFT);
		r.fetch(Estudante_.pessoa, JoinType.LEFT).fetch(Pessoa_.usuario, JoinType.LEFT);
		criteria.where(cb.equal(r.get(Estudante_.codEstudante), codUsuario));
		return em.createQuery(criteria).getSingleResult();
	}
}
