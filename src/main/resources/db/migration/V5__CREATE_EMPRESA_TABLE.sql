CREATE TABLE TB_EMPRESA (
	COD_EMPRESA	SERIAL,
	COD_USUARIO INT NOT NULL UNIQUE,
	RAZAO_SOCIAL	VARCHAR(50)	NOT NULL,
	NOME_FANTASIA	VARCHAR(50),
	CNPJ	VARCHAR(20)	NOT NULL UNIQUE,
	COD_ENDERECO	INT,
	IND_ATIVO	BOOLEAN	DEFAULT 'FALSE',
    DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_empresa PRIMARY KEY(COD_EMPRESA),
	CONSTRAINT fk_empresa_usu FOREIGN KEY(COD_USUARIO) REFERENCES TB_USUARIO(COD_USUARIO),
	CONSTRAINT fk_empresa_endr FOREIGN KEY(COD_ENDERECO) REFERENCES TB_ENDERECO(COD_ENDERECO)
);