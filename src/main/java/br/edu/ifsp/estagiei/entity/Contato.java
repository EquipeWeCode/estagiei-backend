package br.edu.ifsp.estagiei.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
