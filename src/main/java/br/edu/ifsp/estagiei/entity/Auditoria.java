package br.edu.ifsp.estagiei.entity;

import java.time.LocalDateTime;

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

	@Column(name = "DATA_INCLUSAO")
	private LocalDateTime dataInclusao;
	@Column(name = "DATA_ALTERACAO")
	private LocalDateTime dataAlteracao;
}
