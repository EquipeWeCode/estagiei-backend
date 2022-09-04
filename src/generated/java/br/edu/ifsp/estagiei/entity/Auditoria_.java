package br.edu.ifsp.estagiei.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auditoria.class)
public abstract class Auditoria_ {

	public static volatile SingularAttribute<Auditoria, Timestamp> dataInclusao;
	public static volatile SingularAttribute<Auditoria, Timestamp> dataAlteracao;

	public static final String DATA_INCLUSAO = "dataInclusao";
	public static final String DATA_ALTERACAO = "dataAlteracao";

}

