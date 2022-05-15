package br.edu.ifsp.estagiei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@Column(name = "cod_usuario")
	private Integer codUsuario;
	@Column(name = "senha")
	private String senha;
	@Column(name = "papel")
	private String papel;
	@Column(name = "email")
	private String email;
	@Column(name = "ind_ativo")
	private Boolean indAtivo;
	
	@OneToOne(mappedBy = "usuario")
	private Pessoa pessoa;
}
