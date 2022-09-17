package br.edu.ifsp.estagiei.entity.listener;

import br.edu.ifsp.estagiei.entity.Auditoria;

public interface Auditavel {
	Auditoria getAuditoria();

	void setAuditoria(Auditoria auditoria);
}
