CREATE TABLE TB_USUARIO (
	COD_USUARIO SERIAL,
	SENHA	VARCHAR(64) NOT NULL,
	EMAIL	VARCHAR(50) NOT NULL UNIQUE,
	AVATAR	VARCHAR(100),
	TIP_USUARIO VARCHAR(3) NOT NULL,
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE',
	DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_usuario PRIMARY KEY(COD_USUARIO)
);