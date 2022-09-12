package br.edu.ifsp.estagiei.constants;

public enum ModalidadeEnum {
	PRESENCIAL("P"), HIBRIDO("H"), REMOTO("R");

	private final String codigo;

	private ModalidadeEnum(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static ModalidadeEnum getEnum(String codigo) {
		for (ModalidadeEnum modalidade : ModalidadeEnum.values()) {
			if (modalidade.getCodigo().equals(codigo)) {
				return modalidade;
			}
		}
		return null;
	}
}
