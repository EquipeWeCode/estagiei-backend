CREATE TABLE IF NOT EXISTS TB_USUARIO (
	COD_USUARIO SERIAL,
	SENHA	VARCHAR(64) NOT NULL,
	EMAIL	VARCHAR(50) NOT NULL UNIQUE,
	AVATAR	VARCHAR(100),
	IND_ATIVO	BOOLEAN	DEFAULT 'TRUE',
	DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_usuario PRIMARY KEY(COD_USUARIO)
);
                                                                                        /* senha: 1234 */
INSERT INTO TB_USUARIO (COD_USUARIO, EMAIL, SENHA) VALUES (777666, 'wecodetrabalho@gmail.com', '$2a$10$Arj2K2FcDDHgWV/NffeuJ.h47ylM7M22Kctz9GZ0/ed8rmQbZIjsa');
INSERT INTO TB_USUARIO (COD_USUARIO, EMAIL, SENHA) VALUES (777667, 'aluno@aluno.com', '$2a$10$Arj2K2FcDDHgWV/NffeuJ.h47ylM7M22Kctz9GZ0/ed8rmQbZIjsa');
INSERT INTO TB_USUARIO (COD_USUARIO, EMAIL, SENHA) VALUES (777668, 'empresa@empresa.com', '$2a$10$Arj2K2FcDDHgWV/NffeuJ.h47ylM7M22Kctz9GZ0/ed8rmQbZIjsa');