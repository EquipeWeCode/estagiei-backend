package br.edu.ifsp.estagiei.entity.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.edu.ifsp.estagiei.entity.Auditoria;

public class AuditoriaListener {

	@PrePersist
	public void setDataInclusao(Auditavel auditavel) {
		Auditoria auditoria = auditavel.getAuditoria();

		if (auditoria == null) {
			auditoria = new Auditoria();
			auditavel.setAuditoria(auditoria);
		}

		auditoria.setDataInclusao(LocalDateTime.now());
	}

	@PreUpdate
	public void setDataAlteracao(Auditavel auditavel) {
		Auditoria auditoria = auditavel.getAuditoria();
		
		if (auditoria == null) {
			auditoria = new Auditoria();
			auditavel.setAuditoria(auditoria);
		}

		auditoria.setDataAlteracao(LocalDateTime.now());
	}
}
