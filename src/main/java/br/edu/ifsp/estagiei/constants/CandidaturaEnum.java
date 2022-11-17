package br.edu.ifsp.estagiei.constants;

public enum CandidaturaEnum {
	CANDIDATADO("CA"), CANCELADO("CN"), CANCELADO_ESTUDANTE("CE"), APROVADO("AP"), REPROVADO("RP"), FINALIZADO("FN"), DESATIVADO("DS");

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
