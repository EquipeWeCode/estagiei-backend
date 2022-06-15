package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Competencia_;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Estudante_;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.entity.Vaga_;

@Repository
public class VagaRepositoryCustomImpl implements VagaRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	public List<Vaga> buscaVagasRecomendadas(String codEstudante) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);
		
		Join<Vaga, Competencia> joinCompetencias = r.join(Vaga_.competencias, JoinType.LEFT);
		Join<Competencia, Estudante> joinEstudantes = joinCompetencias.join(Competencia_.estudantes, JoinType.LEFT);

		criteria.distinct(true).select(r).where(cb.equal(joinEstudantes.get(Estudante_.codEstudante), codEstudante));
		
		criteria.groupBy(r.get(Vaga_.codVaga));
		criteria.orderBy(cb.asc(r.get(Vaga_.titulo)));

		return em.createQuery(criteria).getResultList();
	}

	@Override
	public List<Vaga> buscaTodosPorFiltro(VagaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);

		r.fetch("competencias", JoinType.LEFT);

		criteria.distinct(true).select(r).where(aplicaFiltros(r, filtro));

		if (filtro.hasOrdem()) {
			if ("DESC".equals(filtro.getOrdemFiltro())) {
				criteria.orderBy(cb.desc(r.get("titulo")));
			} else {
				criteria.orderBy(cb.asc(r.get("titulo")));
			}
		}

		return em.createQuery(criteria).getResultList();
	}

	private Predicate[] aplicaFiltros(Root<Vaga> root, VagaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Predicate> predicates = Lists.newArrayList();

		if (filtro.hasDescricao()) {
			predicates.add(cb.like(cb.upper(root.get("descricao")), filtro.getDescricaoFiltro()));
		}
		if (filtro.hasTitulo()) {
			predicates.add(cb.like(cb.upper(root.get("titulo")), filtro.getTituloFiltro()));
		}

		return predicates.stream().toArray(Predicate[]::new);
	}
}
