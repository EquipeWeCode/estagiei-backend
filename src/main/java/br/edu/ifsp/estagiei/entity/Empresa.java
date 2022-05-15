package br.edu.ifsp.estagiei.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_empresa")
public class Empresa {
	@Id
	@Column(name = "cod_empresa")
	private Integer codEmpresa;
	@Column(name = "razao_social")
	private String razaoSocial;
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	@Column(name = "cnpj")
	private String cnpj;
	@Column(name = "ind_ativo")
	private Boolean indAtivo;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "empresa")
	private Set<Vaga> vagas;
}
