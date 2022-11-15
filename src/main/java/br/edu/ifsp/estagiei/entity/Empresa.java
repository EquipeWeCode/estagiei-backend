package br.edu.ifsp.estagiei.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
import br.edu.ifsp.estagiei.entity.listener.NovaEmpresaListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_empresa")
@EntityListeners(value = { NovaEmpresaListener.class, AuditoriaListener.class })
public class Empresa implements Auditavel {

	@Id
	@SequenceGenerator(name = "tb_empresa_cod_empresa_seq", sequenceName = "tb_empresa_cod_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_empresa_cod_empresa_seq")
	@Column(name = "cod_empresa", updatable = false)
	private Long codEmpresa;
	@Column(name = "cod_usuario", insertable = false, updatable = false)
	private Long codUsuario;
	@Column(name = "razao_social")
	private String razaoSocial;
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	@Column(name = "cnpj")
	private String cnpj;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'FALSE'", nullable = false)
	private Boolean indAtivo = false;
	@Embedded
	private Auditoria auditoria;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "cod_usuario", referencedColumnName = "cod_usuario")
	private Usuario usuario;

	public boolean hasEndereco() {
		return Persistence.getPersistenceUtil().isLoaded(this, "endereco") && endereco != null;
	}

	public boolean isAtiva() {
		return indAtivo != null && indAtivo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEmpresa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Empresa))
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(codEmpresa, other.codEmpresa);
	}
}
