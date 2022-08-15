CREATE TABLE IF NOT EXISTS TB_VAGA (
	COD_VAGA	SERIAL,
	COD_EMPRESA	INT	NOT NULL,
	COD_ENDERECO INT,
	TITULO	VARCHAR(30),
	DESCRICAO	TEXT,
	SALARIO	NUMERIC (9, 2),
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE',
	INCLUIDO_POR VARCHAR(50),
	DATA_INCLUSAO DATETIME,
	ALTERADO_POR VARCHAR(50),
	DATA_ALTERACAO DATETIME,
	CONSTRAINT pk_vaga PRIMARY KEY(COD_VAGA),
	CONSTRAINT fk_vaga_endr FOREIGN KEY(COD_ENDERECO) REFERENCES TB_ENDERECO(COD_ENDERECO),
	CONSTRAINT fk_vaga_emprs FOREIGN KEY(COD_EMPRESA) REFERENCES TB_EMPRESA(COD_EMPRESA)
);

INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777666, 777666, 'Vaga disponível para atuar no desenvolvimento front-end do nosso sistema focado em vagas de estágio!', 3500.98, 'Desenvolvedor Front-End');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777667, 777667, 'Aqui você irá aprender: Java com Spring Boot e Oracle PLSQL, venha conosco nessa jornada!', 2567.42, 'Desenvolvedor Back-End Java');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777668, 777667, 'Se você é dedicado e gosta de aprender, essa vaga é para você!', 1200.56, 'Manutenção de computadores');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777669, 777668, 'Venha com a gente aprender a ser um desenvolvedor web!', 1780.56, 'Desenvolvedor Web');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777670, 777668, 'Estamos em busca de pessoas dedicadas e que queiram aprender sobre Gestão de Recursos Humanos, venha com a gente!', 1831.74, 'Recursos Humanos');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777671, 777666, 'Buscamos alguém interessado em engenharia de software e que queira seguir na área', 2501.23, 'Engenheiro de Software');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777672, 777668, '5 Vagas para pessoas que cursam Engenharia Civil', 1234.63, 'Engenheiro Civil');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777673, 777667, 'Se você busca ser contador e seguir carreira nisso, venha com a gente!', 1262.67, 'Contador');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777674, 777666, '3 vagas para área de logística', 1960.12, 'Logística');
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, DESCRICAO, SALARIO, TITULO) VALUES (777675, 777668, 'Buscamos pessoas que gostem de programação e querem aprender mais sobre Python', 2000.00, 'Desenvolvedor Python');
