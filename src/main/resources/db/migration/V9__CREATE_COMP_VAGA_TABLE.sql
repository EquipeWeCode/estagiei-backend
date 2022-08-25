CREATE TABLE TB_COMP_VAGA(
	COD_COMPETENCIA INT,
	COD_VAGA INT,
	IND_ATIVO BOOLEAN DEFAULT(TRUE),
-- 	INCLUIDO_POR VARCHAR(50),
-- 	DATA_INCLUSAO DATETIME,
-- 	ALTERADO_POR VARCHAR(50),
-- 	DATA_ALTERACAO DATETIME,
	CONSTRAINT pk_comp_vaga PRIMARY KEY(COD_COMPETENCIA, COD_VAGA),
	CONSTRAINT fk_comp_vaga_comp FOREIGN KEY(COD_COMPETENCIA) REFERENCES TB_COMPETENCIA(COD_COMPETENCIA),
	CONSTRAINT fk_comp_vaga_vaga FOREIGN KEY(COD_VAGA) REFERENCES TB_VAGA(COD_VAGA)
);

INSERT INTO tb_comp_vaga VALUES(1, 777666);
INSERT INTO tb_comp_vaga VALUES(2, 777666);
INSERT INTO tb_comp_vaga VALUES(6, 777667);
INSERT INTO tb_comp_vaga VALUES(5, 777667);
INSERT INTO tb_comp_vaga VALUES(10, 777668);
INSERT INTO tb_comp_vaga VALUES(4, 777668);
INSERT INTO tb_comp_vaga VALUES(8, 777669);
INSERT INTO tb_comp_vaga VALUES(9, 777669);
INSERT INTO tb_comp_vaga VALUES(22, 777670);
INSERT INTO tb_comp_vaga VALUES(20, 777670);
INSERT INTO tb_comp_vaga VALUES(14, 777671);
INSERT INTO tb_comp_vaga VALUES(13, 777671);
INSERT INTO tb_comp_vaga VALUES(12, 777671);
INSERT INTO tb_comp_vaga VALUES(9, 777672);
INSERT INTO tb_comp_vaga VALUES(8, 777673);
INSERT INTO tb_comp_vaga VALUES(7, 777673);
INSERT INTO tb_comp_vaga VALUES(6, 777674);
INSERT INTO tb_comp_vaga VALUES(14, 777674);
INSERT INTO tb_comp_vaga VALUES(23, 777675);
INSERT INTO tb_comp_vaga VALUES(25, 777675);