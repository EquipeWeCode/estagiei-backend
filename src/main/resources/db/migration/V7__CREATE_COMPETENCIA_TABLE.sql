CREATE TABLE TB_COMPETENCIA(
	COD_COMPETENCIA SERIAL,
	DESCRICAO VARCHAR(50),
	IND_ATIVO BOOLEAN DEFAULT 'TRUE',
	DATA_INCLUSAO TIMESTAMP,
	DATA_ALTERACAO TIMESTAMP,
	CONSTRAINT pk_competencia PRIMARY KEY(COD_COMPETENCIA)
);

INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('COMUNICAÇÃO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('LIDERANÇA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('FLEXIBILIDADE', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('TRABALHO EM EQUIPE', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('CRIATIVIDADE', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('PROATIVIDADE', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('EMPATIA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('ÉTICA NO TRABALHO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('PENSAMENTO CRÍTICO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('ATITUDE POSITIVA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('DESENVOLVIMENTO DE EQUIPE', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('MOTIVAÇÃO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('INFLUÊNCIA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('CAPACIDADE DE TOMAR DECISÕES', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('CONHECIMENTO POLÍTICO E CULTURAL', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('PODER DE NEGOCIAÇÃO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('ESTABELECIMENTO DE CONFIANÇA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('GERENCIAMENTO DE CONFLITOS', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('BOA ESCRITA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('COLABORAÇÃO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('ORGANIZAÇÃO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('RESILIÊNCIA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('CAPACIDADE DE RESOLVER PROBLEMAS', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('RELACIONAMENTO INTERPESSOAL', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('ADAPTAÇÃO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('HONESTIDADE', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('AUTOGESTÃO', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('AUTOCONFIANÇA', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('INTELIGÊNCIA EMOCIONAL', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('DESENVOLVIMENTO PESSOAL', NOW(), NOW());
INSERT INTO TB_COMPETENCIA(DESCRICAO, DATA_INCLUSAO, DATA_ALTERACAO) VALUES ('INTERESSE EM APRENDER', NOW(), NOW());