package br.edu.ifsp.estagiei.constants;

public enum StatusHistoricoEnum {
	CONCLUIDO("CON"), CURSANDO("CUR"), TRANCADO("TRAN"), DESISTIDO("DES");

	private final String codigo;

	private StatusHistoricoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static StatusHistoricoEnum getEnum(String codigo) {
		for (StatusHistoricoEnum candidatura : StatusHistoricoEnum.values()) {
			if (candidatura.getCodigo().equals(codigo)) {
				return candidatura;
			}
		}
		return null;
	}
}
