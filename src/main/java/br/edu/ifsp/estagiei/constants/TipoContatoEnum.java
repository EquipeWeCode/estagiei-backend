package br.edu.ifsp.estagiei.constants;

public enum TipoContatoEnum {
	TEL_FIXO("FIXO"), CELULAR("CEL"), EMAIL("EMAIL");

	private final String codigo;

	private TipoContatoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static TipoContatoEnum getEnum(String codigo) {
		for (TipoContatoEnum role : TipoContatoEnum.values()) {
			if (role.getCodigo().equals(codigo)) {
				return role;
			}
		}
		return null;
	}
}