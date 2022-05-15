CREATE TABLE TB_PESSOA (
	COD_PESSOA	SERIAL	PRIMARY KEY,
	COD_ENDERECO	SERIAL	REFERENCES TB_ENDERECO(COD_ENDERECO) NOT NULL,
	TIP_CONTATO	VARCHAR(10),	--VAI VIRAR UMA TABELA SEPARADA DE TB_CONTATO
	VALOR_CONTATO	VARCHAR(25),
	NOME	VARCHAR(50)	NOT NULL,
	DT_NASC	DATE	NOT NULL,
	RG	VARCHAR(15)	NOT NULL,
	CPF	VARCHAR(15)	NOT NULL UNIQUE,
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE'
);

INSERT INTO TB_PESSOA(COD_ENDERECO, TIP_CONTATO, VALOR_CONTATO, NOME, DT_NASC, RG, CPF) VALUES (1, 'EMAIL', 'TESTE@TESTE.COM', 'MARIO DA SILVA', '09/12/2003', '24.565.856-6', '856.589.659-82');