package br.edu.ifsp.estagiei.repository;

import br.edu.ifsp.estagiei.entity.Estudante;

public interface EstudanteRepositoryCustom {
	public Estudante buscaPorCodEstudante(String codUsuario);
}
