package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import br.edu.ifsp.estagiei.dto.filter.UsuarioFiltroDTO;
import br.edu.ifsp.estagiei.entity.Usuario;

public interface UsuarioRepositoryCustom {
	public List<Usuario> buscaTodosPorFiltro(UsuarioFiltroDTO filtro);
}
