package br.edu.ifsp.estagiei.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.edu.ifsp.estagiei.constants.TipoContatoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_contato")
public class Contato {

    @Id
    @SequenceGenerator(name = "tb_contato_cod_contato_seq", sequenceName = "tb_contato_cod_contato_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_contato_cod_contato_seq")
    @Column(name = "cod_contato", updatable = false)
    private Long codContato;
    @Column(name = "tip_contato")
    private TipoContatoEnum tipoContato;
    @Column(name = "desc_contato")
    private String descContato;
    @Column(name = "valor_contato")
    private String valorContato;
    @Embedded
    private Auditoria auditoria;
     
	@Override
	public int hashCode() {
		return Objects.hash(codContato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Contato))
			return false;
		Contato other = (Contato) obj;
		if(other.codContato == null) {
			return false;
		}
		return Objects.equals(codContato, other.codContato);
	}
    
}
