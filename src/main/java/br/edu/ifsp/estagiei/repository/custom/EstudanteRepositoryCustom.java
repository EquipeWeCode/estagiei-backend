package br.edu.ifsp.estagiei.repository.custom;

import br.edu.ifsp.estagiei.entity.Estudante;

public interface EstudanteRepositoryCustom {
	public Estudante findByCodEstudante(String codUsuario);
}
