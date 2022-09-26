package br.edu.ifsp.estagiei.constants;

public enum NvlEscolaridadeHistoricoEnum {
	TECNICO("T"), FUNDAMENTAL("F"), MEDIO("M"), SUPERIOR("S");

	private final String codigo;

	private NvlEscolaridadeHistoricoEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static NvlEscolaridadeHistoricoEnum getEnum(String codigo) {
		for (NvlEscolaridadeHistoricoEnum modalidade : NvlEscolaridadeHistoricoEnum.values()) {
			if (modalidade.getCodigo().equals(codigo)) {
				return modalidade;
			}
		}
		return null;
	}
}
