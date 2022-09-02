CREATE TABLE IF NOT EXISTS TB_PESSOA (
	COD_PESSOA	SERIAL,
	COD_USUARIO	INT NOT NULL UNIQUE,
	COD_ENDERECO INT,
	NOME	VARCHAR(50)	NOT NULL,
	DT_NASC	DATE,
	RG	VARCHAR(15),
	CPF	VARCHAR(15) UNIQUE,
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE',
    DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_pessoa PRIMARY KEY(COD_PESSOA),
	CONSTRAINT fk_pessoa_usu FOREIGN KEY(COD_USUARIO) REFERENCES TB_USUARIO(COD_USUARIO),
	CONSTRAINT fk_pessoa_endc FOREIGN KEY(COD_ENDERECO) REFERENCES TB_ENDERECO(COD_ENDERECO)
);

INSERT INTO TB_PESSOA(COD_PESSOA, COD_USUARIO, COD_ENDERECO, NOME, DT_NASC, RG, CPF) VALUES (777666, 777666, 777666, 'WE CODE', '09/12/2003', '24.565.856-6', '238.471.650-62');

