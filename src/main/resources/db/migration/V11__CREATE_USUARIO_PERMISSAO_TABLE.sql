CREATE TABLE IF NOT EXISTS TB_USU_PERM(
	COD_PERMISSAO INT,
	COD_USUARIO INT,
	CONSTRAINT pk_usu_perm PRIMARY KEY(COD_PERMISSAO, COD_USUARIO),
	CONSTRAINT fk_usu_perm_perm FOREIGN KEY(COD_PERMISSAO) REFERENCES TB_PERMISSAO(COD_PERMISSAO),
	CONSTRAINT fk_usu_perm_usu FOREIGN KEY(COD_USUARIO) REFERENCES TB_USUARIO(COD_USUARIO)
);

INSERT INTO TB_USU_PERM(COD_PERMISSAO, COD_USUARIO) VALUES (777666, 777666);
INSERT INTO TB_USU_PERM(COD_PERMISSAO, COD_USUARIO) VALUES (777667, 777667);
INSERT INTO TB_USU_PERM(COD_PERMISSAO, COD_USUARIO) VALUES (777668, 777668);