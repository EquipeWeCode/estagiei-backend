package br.edu.ifsp.estagiei.entity;

import java.util.Objects;

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

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;

	@JsonIgnore
	@OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)
	private Empresa empresa;

	@Override
	public int hashCode() {
		return Objects.hash(codEndereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Endereco))
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(codEndereco, other.codEndereco);
	}
}
