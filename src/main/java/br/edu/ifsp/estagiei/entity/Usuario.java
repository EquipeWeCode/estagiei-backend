package br.edu.ifsp.estagiei.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@SequenceGenerator(name = "tb_usuario_cod_usuario_seq", sequenceName = "tb_usuario_cod_usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_usuario_cod_usuario_seq")
	@Column(name = "cod_usuario", updatable = false, nullable = false)
	private Long codUsuario;
	@Column(name = "senha")
//	@ColumnTransformer( read="decrypt(pswd)" write="encrypt(?)" ) //teste
	private String senha;
	@Column(name = "papel", columnDefinition = "VARCHAR(25)	DEFAULT 'COMUM'", nullable = false)
	private String papel = "COMUM";
	@Column(name = "email")
	private String email;
	@Column(name = "avatar")
	private String avatar;

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE })
	@JsonIgnore
	private Pessoa pessoa;

	@Override
	public int hashCode() {
		return Objects.hash(codUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(codUsuario, other.codUsuario);
	}
}
