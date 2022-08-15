CREATE TABLE IF NOT EXISTS TB_EMPRESA (
	COD_EMPRESA	SERIAL,
	COD_USUARIO INT NOT NULL UNIQUE,
	RAZAO_SOCIAL	VARCHAR(50)	NOT NULL,
	NOME_FANTASIA	VARCHAR(50),
	CNPJ	VARCHAR(20)	NOT NULL UNIQUE,
	COD_ENDERECO	INT,
	IND_ATIVO	BOOLEAN	DEFAULT 'FALSE',
	INCLUIDO_POR VARCHAR(50),
	DATA_INCLUSAO DATETIME,
	ALTERADO_POR VARCHAR(50),
	DATA_ALTERACAO DATETIME,
	CONSTRAINT pk_empresa PRIMARY KEY(COD_EMPRESA),
	CONSTRAINT fk_empresa_usu FOREIGN KEY(COD_USUARIO) REFERENCES TB_USUARIO(COD_USUARIO),
	CONSTRAINT fk_empresa_endr FOREIGN KEY(COD_ENDERECO) REFERENCES TB_ENDERECO(COD_ENDERECO)
);

INSERT INTO TB_EMPRESA(COD_EMPRESA, COD_USUARIO, RAZAO_SOCIAL, NOME_FANTASIA, CNPJ, COD_ENDERECO) VALUES (777666, 777667, 'TESTE LTDA', 'TESTES SEGUROS LTDA', '23.064.687/0001-24', 777666);
INSERT INTO TB_EMPRESA(COD_EMPRESA, COD_USUARIO, RAZAO_SOCIAL, NOME_FANTASIA, CNPJ, COD_ENDERECO) VALUES (777667, 777668, 'LEONARDO LTDA', 'LEONARDO LTDA', '23.064.687/0006-28', 777666);
INSERT INTO TB_EMPRESA(COD_EMPRESA, COD_USUARIO, RAZAO_SOCIAL, NOME_FANTASIA, CNPJ, COD_ENDERECO) VALUES (777668, 777669, 'TONINHO AUTO-PEÇAS LTDA', 'TONINHO AUTO-PEÇAS LTDA', '23.064.689/0301-30', 777666);
