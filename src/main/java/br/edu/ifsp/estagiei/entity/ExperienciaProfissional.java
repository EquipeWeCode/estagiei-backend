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
@Table(name = "tb_exp_profissional")
public class ExperienciaProfissional {

    @Id
    @SequenceGenerator(name = "tb_ext_profissional_cod_exp_profissional_seq", sequenceName = "tb_ext_profissional_cod_exp_profissional_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_ext_profissional_cod_exp_profissional_seq")
    @Column(name = "cod_exp_profissional", updatable = false)
    private Long codExpProfissional;

    @Column(name = "nome_empresa")
    private String nomeEmpresa;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @OneToOne
    private Estudante estudante;

    @ManyToOne
    private Endereco endereco;
}
