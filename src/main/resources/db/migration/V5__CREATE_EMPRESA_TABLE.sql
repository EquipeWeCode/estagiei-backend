CREATE TABLE TB_EMPRESA (
	COD_EMPRESA	SERIAL,
	COD_USUARIO	INT NOT NULL,
	RAZAO_SOCIAL	VARCHAR(50)	NOT NULL,
	NOME_FANTASIA	VARCHAR(50),
	CNPJ	VARCHAR(20)	NOT NULL UNIQUE,
	COD_ENDERECO	INT	NOT NULL,
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE',
	CONSTRAINT pk_empresa PRIMARY KEY(COD_EMPRESA),
	CONSTRAINT fk_empresa_usu FOREIGN KEY(COD_USUARIO) REFERENCES TB_USUARIO(COD_USUARIO),
	CONSTRAINT fk_empresa_endr FOREIGN KEY(COD_ENDERECO) REFERENCES TB_ENDERECO(COD_ENDERECO)
);

INSERT INTO TB_EMPRESA(COD_EMPRESA, COD_USUARIO, RAZAO_SOCIAL, NOME_FANTASIA, CNPJ, COD_ENDERECO) VALUES (1, 2, 'TESTE LTDA', 'TESTES SEGUROS LTDA', '23.064.687/0001-24', 1);