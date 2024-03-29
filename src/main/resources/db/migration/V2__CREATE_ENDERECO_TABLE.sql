CREATE TABLE TB_ENDERECO (
    COD_ENDERECO SERIAL,
	LOGRADOURO	VARCHAR(50)	NOT NULL,
	NUMERO	SMALLINT,
	BAIRRO	VARCHAR(50)	NOT NULL,
	CIDADE	VARCHAR(50)	NOT NULL,
	ESTADO	CHAR(2)	NOT NULL,
	CEP	VARCHAR(9),
	COMPLEMENTO	VARCHAR(50),
	PONTO_REFERENCIA VARCHAR(50),
	IND_ATIVO BOOLEAN DEFAULT 'TRUE',
    DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_endereco PRIMARY KEY(COD_ENDERECO)
);