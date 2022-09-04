package br.edu.ifsp.estagiei.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_permissao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permissao {
	@Id
	@SequenceGenerator(name = "tb_permissao_cod_permissao_seq", sequenceName = "tb_permissao_cod_permissao_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_permissao_cod_permissao_seq")
	@Column(name = "cod_permissao", updatable = false)
	private Long codPermissao;
	@Column(name = "descricao")
	private String descricao;
	@Embedded
	private Auditoria auditoria;
	
	public Permissao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

}
