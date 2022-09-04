CREATE TABLE IF NOT EXISTS TB_USU_PERM(
	COD_PERMISSAO INT,
	COD_USUARIO INT,
    DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_usu_perm PRIMARY KEY(COD_PERMISSAO, COD_USUARIO),
	CONSTRAINT fk_usu_perm_perm FOREIGN KEY(COD_PERMISSAO) REFERENCES TB_PERMISSAO(COD_PERMISSAO),
	CONSTRAINT fk_usu_perm_usu FOREIGN KEY(COD_USUARIO) REFERENCES TB_USUARIO(COD_USUARIO)
);

-- CREATE OR REPLACE FUNCTION TRG_AUDITORIA() RETURNS TRIGGER AS $trg_audit$
--     BEGIN
--         IF (TG_OP = 'UPDATE') THEN
--             UPDATE TB_USU_PERM SET DATA_ALTERACAO = NOW() WHERE COD_PERMISSAO = NEW.COD_PERMISSAO;
--         ELSIF (TG_OP = 'INSERT') THEN
--             UPDATE TB_USU_PERM SET DATA_INCLUSAO = NOW() WHERE COD_PERMISSAO = NEW.COD_PERMISSAO;
--         END IF;
--         RETURN NULL; 
--     END;
-- $trg_audit$ LANGUAGE plpgsql;

-- CREATE TRIGGER trg_audit
-- AFTER INSERT OR UPDATE ON TB_USU_PERM
--     FOR EACH ROW EXECUTE FUNCTION TRG_AUDITORIA();

INSERT INTO TB_USU_PERM(COD_PERMISSAO, COD_USUARIO) VALUES (777666, 777666);
INSERT INTO TB_USU_PERM(COD_PERMISSAO, COD_USUARIO) VALUES (777667, 777667);
INSERT INTO TB_USU_PERM(COD_PERMISSAO, COD_USUARIO) VALUES (777668, 777668);