package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.filter.UsuarioFiltroDTO;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Usuario_;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Usuario> buscaTodosPorFiltro(UsuarioFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> r = criteria.from(Usuario.class);

		criteria.distinct(true).select(r).where(aplicaFiltros(r, filtro));

		return em.createQuery(criteria).getResultList();
	}

	private Predicate[] aplicaFiltros(Root<Usuario> root, UsuarioFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Predicate> predicates = Lists.newArrayList();

		if (filtro.hasEmail()) {
			predicates.add(cb.equal(cb.upper(root.get(Usuario_.email)), filtro.getEmailFiltro()));
		}

		return predicates.stream().toArray(Predicate[]::new);
	}

}
