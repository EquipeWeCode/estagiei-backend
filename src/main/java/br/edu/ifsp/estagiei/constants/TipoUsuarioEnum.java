package br.edu.ifsp.estagiei.constants;

public enum TipoUsuarioEnum {
	ESTUDANTE("EST"), EMPRESA("EMP"), ADMIN("ADM"), REPRESENTANTE("REP");

	private final String codigo;

	private TipoUsuarioEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static TipoUsuarioEnum getEnum(String codigo) {
		for (TipoUsuarioEnum valor : TipoUsuarioEnum.values()) {
			if (valor.getCodigo().equals(codigo)) {
				return valor;
			}
		}
		return null;
	}
}
