package br.edu.ifsp.estagiei.entity.listener;

import java.util.Optional;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.service.EmailServiceImpl;

public class NovaEmpresaListener {

	@Autowired
	private EmailServiceImpl emailService;

	@PostPersist
	@Async
	public void sendEmail(Empresa empresa) {
		Usuario usuarioEmpresa = Optional.ofNullable(empresa.getUsuario()).orElse(new Usuario());
		if (!empresa.isAtiva()) {
			emailService.sendSimpleMessage("wecodetrabalho@gmail.com", "Estagiei - Solicitação de nova empresa",
					 empresa.getNomeFantasia() + " " + "(" + empresa.getCodEmpresa() + " - " + usuarioEmpresa.getEmail() + ")"
							+ " acabou de se cadastrar, faça a análise para ativar ou não essa empresa.");
		}
	}
}
