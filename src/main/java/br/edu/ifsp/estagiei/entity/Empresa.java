package br.edu.ifsp.estagiei.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "tb_empresa")
public class Empresa {
	@Id
	@SequenceGenerator(name = "tb_empresa_cod_empresa_seq", sequenceName = "tb_empresa_cod_empresa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_empresa_cod_empresa_seq")
	@Column(name = "cod_empresa", updatable = false)
	private Long codEmpresa;
	@Column(name = "razao_social")
	private String razaoSocial;
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'FALSE'", nullable = false)
	private Boolean indAtivo = false;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	private Set<Vaga> vagas;
}
