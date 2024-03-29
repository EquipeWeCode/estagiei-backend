package br.edu.ifsp.estagiei.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.entity.id.CandidaturaId;
import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
import br.edu.ifsp.estagiei.entity.listener.SendEmailVagaListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_estud_vaga")
@IdClass(CandidaturaId.class)
@EntityListeners(value = { SendEmailVagaListener.class, AuditoriaListener.class })
public class Candidatura implements Auditavel {

	@Id
	@Column(name = "cod_estudante")
	private Long codEstudante;

	@Id
	@Column(name = "cod_vaga")
	private Long codVaga;

	@Column(name = "status")
	private CandidaturaEnum status;

	@Embedded
	private Auditoria auditoria;

	@ManyToOne
	@MapsId("cod_estudante")
	@JoinColumn(name = "cod_estudante")
	private Estudante estudante;

	@ManyToOne
	@MapsId("cod_vaga")
	@JoinColumn(name = "cod_vaga")
	private Vaga vaga;

}
