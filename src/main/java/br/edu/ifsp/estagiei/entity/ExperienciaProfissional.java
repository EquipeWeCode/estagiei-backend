package br.edu.ifsp.estagiei.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_exp_profissional")
public class ExperienciaProfissional {
	
    @Id
    @SequenceGenerator(name = "tb_exp_profissional_cod_exp_profissional_seq", sequenceName = "tb_exp_profissional_cod_exp_profissional_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_exp_profissional_cod_exp_profissional_seq")
    @Column(name = "cod_exp_profissional", updatable = false)
    private Long codExpProfissional;
    @Column(name = "cod_estudante", insertable = false, updatable = false)
    private Long codEstudante;
    @Column(name = "nome_empresa")
    private String nomeEmpresa;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data_inicio")
    private Timestamp dataInicio;
    @Column(name = "data_fim")
    private Timestamp dataFim;
    @Embedded
    private Auditoria auditoria;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_estudante")
	private Estudante estudante;
    
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;
}
