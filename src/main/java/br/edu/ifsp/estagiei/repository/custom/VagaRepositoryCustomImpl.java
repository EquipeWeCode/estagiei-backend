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

import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
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
	
	public Vaga buscaVagaPorId(Long codVaga) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);

		r.fetch(Vaga_.competencias, JoinType.LEFT);
		r.fetch(Vaga_.empresa, JoinType.INNER);

		criteria.distinct(true).select(r).where(cb.equal(r.get(Vaga_.codVaga), codVaga));

		return em.createQuery(criteria).getSingleResult();
	}

	public List<Vaga> buscaVagasRecomendadas(Long codEstudante) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);

		Join<Vaga, Competencia> joinCompetencias = r.join(Vaga_.competencias, JoinType.LEFT);
		Join<Competencia, Estudante> joinEstudantes = joinCompetencias.join(Competencia_.estudantes, JoinType.LEFT);

		criteria.distinct(true).select(r).where(cb.equal(joinEstudantes.get(Estudante_.codEstudante), codEstudante));

		criteria.groupBy(r.get(Vaga_.codVaga));
		criteria.orderBy(cb.asc(r.get(Vaga_.titulo)));

		List<Vaga> vagasRecomendadas = em.createQuery(criteria).getResultList();

		VagaFiltroDTO filtroVaga = new VagaFiltroDTO();
		List<Long> ids = Lists.newArrayList();

		for (Vaga vaga : vagasRecomendadas) {
			ids.add(vaga.getCodVaga());
		}

		filtroVaga.setIds(ids);
		
		if(filtroVaga.hasIds()) {			
			return buscaTodosPorFiltro(filtroVaga);
		}
		return vagasRecomendadas;
	}

	public List<Vaga> buscaTodosPorFiltro(VagaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vaga> criteria = cb.createQuery(Vaga.class);
		Root<Vaga> r = criteria.from(Vaga.class);

		r.fetch(Vaga_.competencias, JoinType.LEFT);
		r.fetch(Vaga_.empresa, JoinType.INNER);

		criteria.distinct(true).select(r).where(aplicaFiltros(r, filtro));

		if (filtro.hasOrdem()) {
			if ("DESC".equals(filtro.getOrdemFiltro())) {
				criteria.orderBy(cb.desc(r.get(Vaga_.titulo)));
			} else {
				criteria.orderBy(cb.asc(r.get(Vaga_.titulo)));
			}
		}

		return em.createQuery(criteria).getResultList();
	}

	private Predicate[] aplicaFiltros(Root<Vaga> root, VagaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Predicate> predicates = Lists.newArrayList();

		if (filtro.hasDescricao()) {
			predicates.add(cb.like(cb.upper(root.get(Vaga_.descricao)), filtro.getDescricaoFiltro()));
		}
		if (filtro.hasTitulo()) {
			predicates.add(cb.like(cb.upper(root.get(Vaga_.titulo)), filtro.getTituloFiltro()));
		}
		if (filtro.hasIds()) {
			predicates.add(root.get(Vaga_.codVaga).in(filtro.getIds()));
		}

		return predicates.stream().toArray(Predicate[]::new);
	}
}
