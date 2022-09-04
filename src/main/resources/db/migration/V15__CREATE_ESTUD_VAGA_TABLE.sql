CREATE TABLE TB_ESTUD_VAGA(
    COD_ESTUDANTE SERIAL,
    COD_VAGA INT,
    IND_ATIVO BOOLEAN DEFAULT(TRUE),
    STATUS VARCHAR(50),
    CONSTRAINT pk_estud_vaga PRIMARY KEY(COD_ESTUDANTE, COD_VAGA),
    CONSTRAINT fk_estud_vaga_estud FOREIGN KEY(COD_ESTUDANTE) REFERENCES TB_ESTUDANTE(COD_ESTUDANTE),
    CONSTRAINT fk_estud_vaga_vaga FOREIGN KEY(COD_VAGA) REFERENCES TB_VAGA(COD_VAGA)
);