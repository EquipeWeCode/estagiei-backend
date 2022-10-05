package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.filter.EmpresaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Empresa_;
import br.edu.ifsp.estagiei.entity.Endereco;
import br.edu.ifsp.estagiei.entity.Endereco_;
import br.edu.ifsp.estagiei.repository.RepositoryImpl;

@Repository
public class EmpresaRepositoryCustomImpl extends RepositoryImpl implements EmpresaRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	public Page<Empresa> buscaTodosPorFiltro(EmpresaFiltroDTO filtro, Pageable paginacao) {
		EmpresaFiltroDTO novoFiltro = Optional.ofNullable(filtro).orElse(new EmpresaFiltroDTO());
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = cb.createQuery(Empresa.class);
		Root<Empresa> r = criteria.from(Empresa.class);

		r.fetch(Empresa_.endereco, JoinType.LEFT);
		r.fetch(Empresa_.usuario, JoinType.LEFT);

		criteria.distinct(true).select(r).where(aplicaFiltros(r, novoFiltro));

		return (Page<Empresa>) geraPaginacao(paginacao, em.createQuery(criteria).getResultList());
	}

	private Predicate[] aplicaFiltros(Root<Empresa> root, EmpresaFiltroDTO filtro) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		List<Predicate> predicates = Lists.newArrayList();

		if (filtro.hasCodEmpresa()) {
			predicates.add(cb.equal(root.get(Empresa_.codEmpresa), filtro.getCodEmpresa()));
		}
		if (filtro.hasCnpj()) {
			predicates.add(cb.equal(root.get(Empresa_.cnpj), filtro.getCnpj()));
		}
		if (filtro.hasNomeFantasia()) {
			predicates.add(cb.like(root.get(Empresa_.nomeFantasia), filtro.getNomeFiltro()));
		}
		if (filtro.hasIndAtivo()) {
			predicates.add(cb.equal(root.get(Empresa_.indAtivo), filtro.getIndAtivo()));
		}
		if (filtro.hasFiltroEndereco()) {
			aplicaFiltrosEndereco(filtro, cb, predicates, root);
		}

		return predicates.stream().toArray(Predicate[]::new);
	}

	private void aplicaFiltrosEndereco(EmpresaFiltroDTO filtro, CriteriaBuilder cb, List<Predicate> predicates,
			Root<Empresa> root) {
		Join<Empresa, Endereco> endereco = root.join(Empresa_.endereco, JoinType.LEFT);
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
