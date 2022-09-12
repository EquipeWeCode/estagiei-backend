package br.edu.ifsp.estagiei.entity;

import br.edu.ifsp.estagiei.constants.TipoContatoEnum;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contato.class)
public abstract class Contato_ {

	public static volatile SingularAttribute<Contato, String> valorContato;
	public static volatile SingularAttribute<Contato, TipoContatoEnum> tipoContato;
	public static volatile SingularAttribute<Contato, String> descContato;
	public static volatile SingularAttribute<Contato, Long> codContato;
	public static volatile SingularAttribute<Contato, Auditoria> auditoria;

	public static final String VALOR_CONTATO = "valorContato";
	public static final String TIPO_CONTATO = "tipoContato";
	public static final String DESC_CONTATO = "descContato";
	public static final String COD_CONTATO = "codContato";
	public static final String AUDITORIA = "auditoria";

}

