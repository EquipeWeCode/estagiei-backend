package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, String> pontoReferencia;
	public static volatile SingularAttribute<Endereco, String> cidade;
	public static volatile SingularAttribute<Endereco, String> estado;
	public static volatile SingularAttribute<Endereco, String> complemento;
	public static volatile SingularAttribute<Endereco, Integer> numero;
	public static volatile SingularAttribute<Endereco, String> logradouro;
	public static volatile SingularAttribute<Endereco, String> bairro;
	public static volatile SingularAttribute<Endereco, Boolean> indAtivo;
	public static volatile SingularAttribute<Endereco, Auditoria> auditoria;
	public static volatile SingularAttribute<Endereco, Long> codEndereco;
	public static volatile SingularAttribute<Endereco, String> cep;

	public static final String PONTO_REFERENCIA = "pontoReferencia";
	public static final String CIDADE = "cidade";
	public static final String ESTADO = "estado";
	public static final String COMPLEMENTO = "complemento";
	public static final String NUMERO = "numero";
	public static final String LOGRADOURO = "logradouro";
	public static final String BAIRRO = "bairro";
	public static final String IND_ATIVO = "indAtivo";
	public static final String AUDITORIA = "auditoria";
	public static final String COD_ENDERECO = "codEndereco";
	public static final String CEP = "cep";

}

