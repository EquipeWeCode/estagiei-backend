package br.edu.ifsp.estagiei.entity.listener;

import java.util.Optional;

import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.service.EmailServiceImpl;

public class SendEmailVagaListener {

	@Autowired
	private EmailServiceImpl emailService;

	@PostUpdate
	public void sendEmail(Candidatura candidatura) {
		Vaga vaga = candidatura.getVaga();
		Estudante estudante = candidatura.getEstudante();
		Pessoa pessoaEstudante = Optional.ofNullable(estudante.getPessoa()).orElse(new Pessoa());
		Usuario usuarioPessoa = Optional.ofNullable(pessoaEstudante.getUsuario()).orElse(new Usuario());
		if (usuarioPessoa.hasEmail()) {
			emailService.sendMailWithAttachment(usuarioPessoa.getEmail(),
					"Estagiei - Aviso de alteração de status da candidatura",
					"O status da vaga: " + vaga.toString() + " foi alterado para: " + candidatura.getStatus());
		}
	}
}
