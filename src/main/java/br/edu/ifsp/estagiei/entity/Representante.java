package br.edu.ifsp.estagiei.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_representante")
@Getter
@Setter
@NoArgsConstructor
public class Representante {
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
}
