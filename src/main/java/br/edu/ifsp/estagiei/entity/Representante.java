package br.edu.ifsp.estagiei.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_representante")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditoriaListener.class)
public class Representante implements Auditavel {
    @Id
    @Column(name = "cod_representante", updatable = false)
    private Long codRepresentante;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
    private Boolean indAtivo = true;
    @Embedded
    private Auditoria auditoria;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "cod_pessoa")
    @JsonIgnore
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "cod_empresa")
    @JsonIgnore
    private Empresa empresa;
    
	@Override
	public int hashCode() {
		return Objects.hash(codRepresentante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Representante))
			return false;
		Representante other = (Representante) obj;
		return Objects.equals(codRepresentante, other.codRepresentante);
	}
}
