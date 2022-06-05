CREATE TABLE TB_COMPETENCIA(
	COD_COMPETENCIA SERIAL,
	DESCRICAO VARCHAR(50),
	IND_ATIVO BOOLEAN DEFAULT(TRUE),
	CONSTRAINT pk_competencia PRIMARY KEY(COD_COMPETENCIA)
);

INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('COMUNICAÇÃO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('LIDERANÇA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('FLEXIBILIDADE');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('TRABALHO EM EQUIPE');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('CRIATIVIDADE');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('PROATIVIDADE');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('EMPATIA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('ÉTICA NO TRABALHO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('PENSAMENTO CRÍTICO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('ATITUDE POSITIVA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('DESENVOLVIMENTO DE EQUIPE');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('MOTIVAÇÃO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('INFLUÊNCIA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('CAPACIDADE DE TOMAR DECISÕES');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('CONHECIMENTO POLÍTICO E CULTURAL');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('PODER DE NEGOCIAÇÃO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('ESTABELECIMENTO DE CONFIANÇA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('GERENCIAMENTO DE CONFLITOS');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('BOA ESCRITA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('COLABORAÇÃO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('ORGANIZAÇÃO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('FLEXIBILIDADE');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('RESILIÊNCIA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('CAPACIDADE DE RESOLVER PROBLEMAS');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('RELACIONAMENTO INTERPESSOAL');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('ADAPTAÇÃO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('HONESTIDADE');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('AUTOGESTÃO');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('AUTOCONFIANÇA');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('INTELIGÊNCIA EMOCIONAL');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('DESENVOLVIMENTO PESSOAL');
INSERT INTO TB_COMPETENCIA(DESCRICAO) VALUES ('INTERESSE EM APRENDER');