package br.edu.ifsp.estagiei.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Auditoria {
	
	@Column(name = "DATA_INCLUSAO")
	private Timestamp dataInclusao;
	@Column(name = "DATA_ALTERACAO")
	private Timestamp dataAlteracao;
}
