package br.edu.ifsp.estagiei.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, String> rg;
	public static volatile SingularAttribute<Pessoa, Boolean> indAtivo;
	public static volatile SingularAttribute<Pessoa, String> cpf;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, Usuario> usuario;
	public static volatile SingularAttribute<Pessoa, LocalDate> dataNascimento;
	public static volatile SingularAttribute<Pessoa, Estudante> estudante;
	public static volatile SingularAttribute<Pessoa, Long> codPessoa;

	public static final String RG = "rg";
	public static final String IND_ATIVO = "indAtivo";
	public static final String CPF = "cpf";
	public static final String NOME = "nome";
	public static final String USUARIO = "usuario";
	public static final String DATA_NASCIMENTO = "dataNascimento";
	public static final String ESTUDANTE = "estudante";
	public static final String COD_PESSOA = "codPessoa";

}

