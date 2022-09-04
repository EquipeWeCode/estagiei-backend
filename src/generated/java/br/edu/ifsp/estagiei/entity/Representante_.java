package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Representante.class)
public abstract class Representante_ {

	public static volatile SingularAttribute<Representante, Pessoa> pessoa;
	public static volatile SingularAttribute<Representante, Boolean> indAtivo;
	public static volatile SingularAttribute<Representante, String> cargo;
	public static volatile SingularAttribute<Representante, Empresa> empresa;
	public static volatile SingularAttribute<Representante, String> codRepresentante;

	public static final String PESSOA = "pessoa";
	public static final String IND_ATIVO = "indAtivo";
	public static final String CARGO = "cargo";
	public static final String EMPRESA = "empresa";
	public static final String COD_REPRESENTANTE = "codRepresentante";

}

