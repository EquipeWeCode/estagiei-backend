package br.edu.ifsp.estagiei.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.edu.ifsp.estagiei.model.Estudante;

@Repository
public class EstudanteRepositorio {

	@PersistenceContext
	EntityManager em;

	public List<Estudante> listarEstudantes() {
		List<Estudante> estudante = em.createQuery("from Estudante", Estudante.class).getResultList();
		return estudante;
	}
}
