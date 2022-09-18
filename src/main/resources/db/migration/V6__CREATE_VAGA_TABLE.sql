CREATE TABLE TB_VAGA (
	COD_VAGA	SERIAL,
	COD_EMPRESA	INT	NOT NULL,
	COD_ENDERECO INT,
	TITULO	VARCHAR(30),
	DESCRICAO	TEXT,
	SALARIO	NUMERIC (9, 2),
	CURSO VARCHAR(50),
	CARGA_HORARIA INT,
	MODALIDADE CHAR,
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE',
    DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_vaga PRIMARY KEY(COD_VAGA),
	CONSTRAINT fk_vaga_endr FOREIGN KEY(COD_ENDERECO) REFERENCES TB_ENDERECO(COD_ENDERECO),
	CONSTRAINT fk_vaga_emprs FOREIGN KEY(COD_EMPRESA) REFERENCES TB_EMPRESA(COD_EMPRESA)
);

INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777666, 777668, 777666, 'Vaga disponível para atuar no desenvolvimento front-end do nosso sistema focado em vagas de estágio!', 3500.98, 'Desenvolvedor Front-End', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777667, 777668, 777667, 'Aqui você irá aprender: Java com Spring Boot e Oracle PLSQL, venha conosco nessa jornada!', 2567.42, 'Desenvolvedor Back-End Java', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777668, 777668, 777666, 'Se você é dedicado e gosta de aprender, essa vaga é para você!', 1200.56, 'Manutenção de computadores', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777669, 777668, 777667, 'Venha com a gente aprender a ser um desenvolvedor web!', 1780.56, 'Desenvolvedor Web', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777670, 777668, 777668, 'Estamos em busca de pessoas dedicadas e que queiram aprender sobre Gestão de Recursos Humanos, venha com a gente!', 1831.74, 'Recursos Humanos', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777671, 777668, 777669, 'Buscamos alguém interessado em engenharia de software e que queira seguir na área', 2501.23, 'Engenheiro de Software', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777672, 777668, 777670, '5 Vagas para pessoas que cursam Engenharia Civil', 1234.63, 'Engenheiro Civil', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777673, 777668, 777668, 'Se você busca ser contador e seguir carreira nisso, venha com a gente!', 1262.67, 'Contador', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777674, 777668, 777667, '3 vagas para área de logística', 1960.12, 'Logística', NOW(), NOW());
INSERT INTO TB_VAGA(COD_VAGA, COD_EMPRESA, COD_ENDERECO, DESCRICAO, SALARIO, TITULO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES (777675, 777668, 777670, 'Buscamos pessoas que gostem de programação e querem aprender mais sobre Python', 2000.00, 'Desenvolvedor Python', NOW(), NOW());
