package br.edu.ifsp.estagiei.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_pessoa")
@EntityListeners(AuditoriaListener.class)
public class Pessoa implements Auditavel {
	@Id
	@SequenceGenerator(name = "tb_pessoa_cod_pessoa_seq", sequenceName = "tb_pessoa_cod_pessoa_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_pessoa_cod_pessoa_seq")
	@Column(name = "cod_pessoa", updatable = false)
	private Long codPessoa;
	@Column(name = "nome")
	private String nome;
	@Column(name = "dt_nasc", columnDefinition = "DATE")
	private LocalDate dataNascimento;
	@Column(name = "rg")
	private String rg;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	@Embedded
	private Auditoria auditoria;

	@OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "cod_usuario")
	private Usuario usuario;

	@OneToOne(mappedBy = "pessoa", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private Estudante estudante;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "tb_cont_pessoa", joinColumns = @JoinColumn(name = "cod_pessoa"), inverseJoinColumns = @JoinColumn(name = "cod_contato"))
	private Set<Contato> contatos = new HashSet<>();

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;

	public Contato novoContato(Long chavePrimaria) {
		Contato novaEntidade = new Contato();
		novaEntidade.setCodContato(chavePrimaria);

		Contato entidade = contatos.stream().filter(v -> v.equals(novaEntidade)).findFirst().orElse(novaEntidade);
		contatos.add(entidade);
		return entidade;
	}

	public void retemContatos(List<Contato> lista) {
		contatos.retainAll(lista);
	}
	
	public boolean hasEndereco() {
		return Persistence.getPersistenceUtil().isLoaded(this, "endereco") && endereco != null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codPessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(codPessoa, other.codPessoa);
	}

	public boolean hasEstudante() {
		return this.estudante != null;
	}
}
