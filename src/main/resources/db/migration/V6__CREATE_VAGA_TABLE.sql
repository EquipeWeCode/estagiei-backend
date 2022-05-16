CREATE TABLE TB_VAGA (
	COD_VAGA	SERIAL,
	COD_EMPRESA	INT	NOT NULL,
	DESCRICAO	TEXT,
	SALARIO	NUMERIC (9, 2),
	TITULO	VARCHAR(30),
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE',
	CONSTRAINT pk_vaga PRIMARY KEY(COD_VAGA),
	CONSTRAINT fk_vaga_emprs FOREIGN KEY(COD_EMPRESA) REFERENCES TB_EMPRESA(COD_EMPRESA)
);

INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (1, 1, 'Vaga disponível para atuar no desenvolvimento front-end do nosso sistema focado em vagas de estágio!', 3500.98, 'Desenvolvedor Front-End');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (2, 1, 'Aqui você irá aprender: Java com Spring Boot e Oracle PLSQL, venha conosco nessa jornada!', 2567.42, 'Desenvolvedor Back-End Java');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (3, 1, 'Se você é dedicado e gosta de aprender, essa vaga é para você!', 1200.56, 'Manutenção de computadores');
