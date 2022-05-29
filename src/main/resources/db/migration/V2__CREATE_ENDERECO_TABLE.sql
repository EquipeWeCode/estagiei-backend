CREATE TABLE IF NOT EXISTS TB_ENDERECO (
    COD_ENDERECO	SERIAL,
	LOGRADOURO	VARCHAR(50)	NOT NULL,
	NUMERO	SMALLINT,
	BAIRRO	VARCHAR(50)	NOT NULL,
	CIDADE	VARCHAR(50)	NOT NULL,
	ESTADO	CHAR(2)	NOT NULL,
	CEP	INT	NOT NULL,
	COMPLEMENTO	VARCHAR(50),
	IND_ATIVO	BOOLEAN DEFAULT 'TRUE',
	CONSTRAINT pk_endereco PRIMARY KEY(COD_ENDERECO)
);

INSERT INTO TB_ENDERECO (COD_ENDERECO, LOGRADOURO, NUMERO, BAIRRO, CIDADE, ESTADO, CEP) VALUES (1, 'RUA DO TESTE', 123, 'FLORES', 'SÃO PAULO', 'SP', 064235689);