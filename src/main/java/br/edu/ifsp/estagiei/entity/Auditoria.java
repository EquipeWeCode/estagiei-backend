package br.edu.ifsp.estagiei.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auditoria {

	@Column(name = "DATA_INCLUSAO", insertable = false, updatable = false)
	private Timestamp dataInclusao;
	@Column(name = "DATA_ALTERACAO", insertable = false, updatable = false)
	private Timestamp dataAlteracao;
}
