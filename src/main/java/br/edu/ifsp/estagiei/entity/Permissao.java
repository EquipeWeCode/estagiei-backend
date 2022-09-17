package br.edu.ifsp.estagiei.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
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
@EntityListeners(AuditoriaListener.class)
public class Permissao implements Auditavel {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(codPermissao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Permissao))
			return false;
		Permissao other = (Permissao) obj;
		return Objects.equals(codPermissao, other.codPermissao);
	}

}
