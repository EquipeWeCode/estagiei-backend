CREATE TABLE TB_CONT_PESSOA(
       COD_CONTATO INT,
       COD_PESSOA INT,
       CONSTRAINT pk_cont_pessoa PRIMARY KEY(COD_CONTATO, COD_PESSOA),
       CONSTRAINT fk_cont_pessoa_cont FOREIGN KEY(COD_CONTATO) REFERENCES TB_CONTATO(COD_CONTATO),
       CONSTRAINT fk_cont_pessoa_pessoa FOREIGN KEY(COD_PESSOA) REFERENCES TB_PESSOA(COD_PESSOA)
);