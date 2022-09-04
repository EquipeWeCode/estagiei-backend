package br.edu.ifsp.estagiei.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
    private String tipContato;
    @Column(name = "desc_contato")
    private String descContato;
    @Column(name = "valor_contato")
    private String valorContato;
    @Embedded
	private Auditoria auditoria;
    
}
