package br.edu.ifsp.estagiei.constants;

public enum NvlEscolaridadeEnum {
	SEM_INSTRUCAO("NA"), FUNDAMENTAL_INCOMPLETO("FI"), FUNDAMENTAL_COMPLETO("FC"), MEDIO_INCOMPLETO("MI"),
	MEDIO_COMPLETO("MC"), SUPERIOR_INCOMPLETO("SI"), SUPERIOR_COMPLETO("SC");

	private final String codigo;

	private NvlEscolaridadeEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static NvlEscolaridadeEnum getEnum(String codigo) {
		for (NvlEscolaridadeEnum modalidade : NvlEscolaridadeEnum.values()) {
			if (modalidade.getCodigo().equals(codigo)) {
				return modalidade;
			}
		}
		return null;
	}
}
