package br.edu.ifsp.estagiei.constants;

public enum CandidaturaEnum {
	CANDIDATADO("C");

	private final String codigo;

	private CandidaturaEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static CandidaturaEnum getEnum(String codigo) {
		for (CandidaturaEnum candidatura : CandidaturaEnum.values()) {
			if (candidatura.getCodigo().equals(codigo)) {
				return candidatura;
			}
		}
		return null;
	}
}
