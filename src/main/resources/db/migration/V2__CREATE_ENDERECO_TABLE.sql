CREATE TABLE IF NOT EXISTS TB_ENDERECO (
    COD_ENDERECO	SERIAL,
	LOGRADOURO	VARCHAR(50)	NOT NULL,
	NUMERO	SMALLINT,
	BAIRRO	VARCHAR(50)	NOT NULL,
	CIDADE	VARCHAR(50)	NOT NULL,
	ESTADO	CHAR(2)	NOT NULL,
	CEP	INT	NOT NULL,
	COMPLEMENTO	VARCHAR(50),
	PONTO_REFERENCIA VARCHAR(50),
	IND_ATIVO	BOOLEAN DEFAULT 'TRUE',
    DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_endereco PRIMARY KEY(COD_ENDERECO)
);

-- CREATE OR REPLACE FUNCTION TRG_AUDITORIA() RETURNS TRIGGER AS $trg_audit$
--     BEGIN
--         IF (TG_OP = 'UPDATE') THEN
--             UPDATE TB_ENDERECO SET DATA_ALTERACAO = NOW() WHERE COD_ENDERECO = NEW.COD_ENDERECO;
--         ELSIF (TG_OP = 'INSERT') THEN
--             UPDATE TB_ENDERECO SET DATA_INCLUSAO = NOW() WHERE COD_ENDERECO = NEW.COD_ENDERECO;
--         END IF;
--         RETURN NULL; 
--     END;
-- $trg_audit$ LANGUAGE plpgsql;

-- CREATE TRIGGER trg_audit
-- AFTER INSERT OR UPDATE ON TB_ENDERECO
--     FOR EACH ROW EXECUTE FUNCTION TRG_AUDITORIA();

INSERT INTO TB_ENDERECO (COD_ENDERECO, LOGRADOURO, NUMERO, BAIRRO, CIDADE, ESTADO, CEP) VALUES (777666, 'RUA DO TESTE', 123, 'FLORES', 'SÃO PAULO', 'SP', 064235689);