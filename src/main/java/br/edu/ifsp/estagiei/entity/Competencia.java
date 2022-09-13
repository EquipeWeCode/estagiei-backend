package br.edu.ifsp.estagiei.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_competencia")
@Getter
@Setter
@NoArgsConstructor
public class Competencia {
	@Id
	@SequenceGenerator(name = "tb_competencia_cod_contato_seq", sequenceName = "tb_competencia_cod_contato_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_competencia_cod_contato_seq")
	@Column(name = "cod_competencia")
	private Long codCompetencia;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	@Embedded
	private Auditoria auditoria;

	@ManyToMany(mappedBy = "competencias", fetch = FetchType.LAZY)
	private Set<Vaga> vagas = Sets.newHashSet();
	@ManyToMany(mappedBy = "competencias", fetch = FetchType.LAZY)
	private Set<Estudante> estudantes = Sets.newHashSet();
	
	@Override
	public int hashCode() {
		return Objects.hash(codCompetencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Competencia))
			return false;
		Competencia other = (Competencia) obj;
		return Objects.equals(codCompetencia, other.codCompetencia);
	}
}
