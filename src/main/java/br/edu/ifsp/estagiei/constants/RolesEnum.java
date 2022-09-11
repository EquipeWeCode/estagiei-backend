package br.edu.ifsp.estagiei.constants;

public enum RolesEnum {
	ADMIN(777666L), ESTUDANTE(777667L), EMPRESA(777668L);

	private final Long codigo;

	private RolesEnum(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public static RolesEnum getEnum(Long codigo) {
		for (RolesEnum role : RolesEnum.values()) {
			if (role.getCodigo().equals(codigo)) {
				return role;
			}
		}
		return null;
	}
}
