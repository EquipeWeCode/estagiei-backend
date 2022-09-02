package br.edu.ifsp.estagiei.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_representante")
@Getter
@Setter
@NoArgsConstructor
public class Representante {
    @Id
    @Column(name = "cod_representante", updatable = false)
    private String codRepresentante;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
    private Boolean indAtivo = true;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "cod_pessoa")
    @JsonIgnore
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "cod_empresa")
    @JsonIgnore
    private Empresa empresa;
}
