package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Long> codUsuario;
	public static volatile SingularAttribute<Usuario, Pessoa> pessoa;
	public static volatile SingularAttribute<Usuario, Boolean> indAtivo;
	public static volatile SingularAttribute<Usuario, String> avatar;
	public static volatile SingularAttribute<Usuario, String> papel;
	public static volatile SingularAttribute<Usuario, String> email;

	public static final String SENHA = "senha";
	public static final String COD_USUARIO = "codUsuario";
	public static final String PESSOA = "pessoa";
	public static final String IND_ATIVO = "indAtivo";
	public static final String AVATAR = "avatar";
	public static final String PAPEL = "papel";
	public static final String EMAIL = "email";

}

