CREATE TABLE TB_COMP_ESTUD(
	COD_COMPETENCIA INT,
	COD_ESTUDANTE INT,
    DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_comp_estud PRIMARY KEY(COD_COMPETENCIA, COD_ESTUDANTE),
	CONSTRAINT fk_comp_estud_comp FOREIGN KEY(COD_COMPETENCIA) REFERENCES TB_COMPETENCIA(COD_COMPETENCIA),
	CONSTRAINT fk_comp_estud_estud FOREIGN KEY(COD_ESTUDANTE) REFERENCES TB_ESTUDANTE(COD_ESTUDANTE)
);