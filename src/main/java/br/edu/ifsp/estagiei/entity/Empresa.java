package br.edu.ifsp.estagiei.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "cod_usuario")
	private Long codUsuario = 777666L; //Apenas para questões de teste, assim que o login de empresas estiver pronto, mudar
	
	@Column(name = "razao_social")
	private String razaoSocial;
	@Column(name = "nome_fantasia")
	private String nomeFantasia;
	@Column(name = "cnpj")
	private String cnpj;

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'FALSE'", nullable = false)
	private Boolean indAtivo = false;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "empresa")
	private Set<Vaga> vagas;
	
	public boolean hasVagas() {
		return vagas != null && !vagas.isEmpty();
	}
}
