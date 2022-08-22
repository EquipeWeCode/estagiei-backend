package br.edu.ifsp.estagiei.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @SequenceGenerator(name = "tb_curso_cod_curso_seq", sequenceName = "tb_curso_cod_curso_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_curso_cod_curso_seq")
    @Column(name = "cod_curso", updatable = false)
    private Long codCurso;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo_curso")
    private String tipoCurso;

    @Column(name = "ind_ativo")
    private Boolean indAtivo;
}
