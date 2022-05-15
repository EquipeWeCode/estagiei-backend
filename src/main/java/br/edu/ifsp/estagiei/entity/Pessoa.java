package br.edu.ifsp.estagiei.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class Pessoa {
	@Id
	@Column(name = "cod_pessoa")
	private Integer codPessoa;
	@Column(name = "dt_nasc")
	private String dataNascimento;
	@Column(name = "rg")
	private String rg;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "nome")
	private String nome;
	@Column(name = "tip_contato")
	private String tipContato;
	@Column(name = "valor_contato")
	private String valorContato;
	@Column(name = "ind_ativo")
	private Boolean indAtivo;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "cod_estudante")
	private Estudante estudante;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "cod_usuario")
	private Usuario usuario;
}
