package br.edu.ifsp.estagiei.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifsp.estagiei.dto.filter.EmpresaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Empresa;

public interface EmpresaRepositoryCustom {

	public Page<Empresa> buscaTodosPorFiltro(EmpresaFiltroDTO filtro, Pageable paginacao);
	
}
