package br.edu.ifsp.estagiei.constants;

public enum OperacaoAdministradorEnum {
	APROVAR("APROVAR"), INATIVAR("INATIVAR");

	private final String codigo;

	private OperacaoAdministradorEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static OperacaoAdministradorEnum getEnum(String codigo) {
		for (OperacaoAdministradorEnum operacao : OperacaoAdministradorEnum.values()) {
			if (operacao.getCodigo().equals(codigo)) {
				return operacao;
			}
		}
		return null;
	}
}
