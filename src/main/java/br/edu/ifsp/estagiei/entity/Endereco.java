package br.edu.ifsp.estagiei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class Endereco {
	@Id
	@SequenceGenerator(name = "tb_endereco_cod_endereco_seq", sequenceName = "tb_endereco_cod_endereco_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_endereco_cod_endereco_seq")
	@Column(name = "cod_endereco", updatable = false)
	private Long codEndereco;
	@Column(name = "logradouro")
	private String logradouro;
	@Column(name = "numero")
	private String numero;
	@Column(name = "bairro")
	private String bairro;
	@Column(name = "cidade")
	private String cidade;
	@Column(name = "estado")
	private String estado;
	@Column(name = "cep")
	private Integer cep;
	@Column(name = "complemento")
	private String complemento;
	@Column(name = "ind_ativo")
	private Boolean indAtivo;
	
	@OneToOne(mappedBy = "endereco")
	private Empresa empresa;
}
