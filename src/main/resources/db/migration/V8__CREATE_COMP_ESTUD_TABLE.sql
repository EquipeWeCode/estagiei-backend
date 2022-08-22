CREATE TABLE TB_COMP_ESTUD(
	COD_COMPETENCIA INT,
	COD_ESTUDANTE INT,
	IND_ATIVO BOOLEAN DEFAULT(TRUE),
	INCLUIDO_POR VARCHAR(50),
	DATA_INCLUSAO DATETIME,
	ALTERADO_POR VARCHAR(50),
	DATA_ALTERACAO DATETIME,
	CONSTRAINT pk_comp_estud PRIMARY KEY(COD_COMPETENCIA, COD_ESTUDANTE),
	CONSTRAINT fk_comp_estud_comp FOREIGN KEY(COD_COMPETENCIA) REFERENCES TB_COMPETENCIA(COD_COMPETENCIA),
	CONSTRAINT fk_comp_estud_estud FOREIGN KEY(COD_ESTUDANTE) REFERENCES TB_ESTUDANTE(COD_ESTUDANTE)
);

INSERT INTO TB_COMP_ESTUD(COD_COMPETENCIA, COD_ESTUDANTE) VALUES (1, 777666);
INSERT INTO TB_COMP_ESTUD(COD_COMPETENCIA, COD_ESTUDANTE) VALUES (5, 777666);
INSERT INTO TB_COMP_ESTUD(COD_COMPETENCIA, COD_ESTUDANTE) VALUES (10, 777666);