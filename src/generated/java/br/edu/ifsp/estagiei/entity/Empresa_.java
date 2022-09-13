package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Empresa.class)
public abstract class Empresa_ {

	public static volatile SingularAttribute<Empresa, Long> codUsuario;
	public static volatile SingularAttribute<Empresa, String> nomeFantasia;
	public static volatile SingularAttribute<Empresa, Endereco> endereco;
	public static volatile SingularAttribute<Empresa, Long> codEmpresa;
	public static volatile SingularAttribute<Empresa, Boolean> indAtivo;
	public static volatile SingularAttribute<Empresa, Usuario> usuario;
	public static volatile SingularAttribute<Empresa, String> cnpj;
	public static volatile SingularAttribute<Empresa, Auditoria> auditoria;
	public static volatile SingularAttribute<Empresa, String> razaoSocial;

	public static final String COD_USUARIO = "codUsuario";
	public static final String NOME_FANTASIA = "nomeFantasia";
	public static final String ENDERECO = "endereco";
	public static final String COD_EMPRESA = "codEmpresa";
	public static final String IND_ATIVO = "indAtivo";
	public static final String USUARIO = "usuario";
	public static final String CNPJ = "cnpj";
	public static final String AUDITORIA = "auditoria";
	public static final String RAZAO_SOCIAL = "razaoSocial";

}

