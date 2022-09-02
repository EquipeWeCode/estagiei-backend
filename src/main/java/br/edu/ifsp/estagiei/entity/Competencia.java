package br.edu.ifsp.estagiei.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	@Column(name = "cod_competencia")
	private Long codCompetencia;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	@Embedded
	private Auditoria auditoria;

	@ManyToMany(mappedBy = "competencias", fetch = FetchType.LAZY)
	private Set<Vaga> vagas = new HashSet<>();
	@ManyToMany(mappedBy = "competencias", fetch = FetchType.LAZY)
	private Set<Estudante> estudantes = new HashSet<>();
	
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
