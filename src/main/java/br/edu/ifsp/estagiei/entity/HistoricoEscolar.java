package br.edu.ifsp.estagiei.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_hist_escolar")
public class HistoricoEscolar {

    @Id
    @SequenceGenerator(name = "tb_hist_escolar_cod_hist_escolar_seq", sequenceName = "tb_hist_escolar_cod_hist_escolar_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_empresa_cod_empresa_seq")
    @Column(name = "cod_hist_escolar", updatable = false)
    private Long codHistEscolar;

    @Column(name = "nvl_escolaridade", updatable = false)
    private String nvlEscolaridade;

    @Column(name = "inst_ensino", updatable = false)
    private String instEnsino;

    @Column(name = "data_inicio", updatable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", updatable = false)
    private LocalDate dataFim;

    @Column(name = "status", updatable = false)
    private String status;

    @ManyToOne
    private Curso curso;

    @OneToOne
    private Estudante estudante;
}
