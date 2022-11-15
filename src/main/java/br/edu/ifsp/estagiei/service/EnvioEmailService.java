package br.edu.ifsp.estagiei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;

@Service
public class EnvioEmailService {
	@Autowired
	private EmailServiceImpl emailService;

	@Async
	public void sendEmailEmpresa(EmpresaDTO dto) {
		if (dto.isAtiva()) {
			emailService.sendSimpleMessage(dto.getEmail(), "Estagiei",
					"Sua solicitação foi aprovada! Agora você pode entrar em nosso site e divulgar suas vagas de maneira simples e rápida!");
		}
	}

}
