CREATE SEQUENCE seq_empresa START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE SEQUENCE seq_unidade START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE SEQUENCE seq_leilao START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;
CREATE SEQUENCE seq_lote START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE TABLE empresa (
    id INTEGER NOT NULL DEFAULT nextval('seq_empresa'),
    razao_social VARCHAR(64) NOT NULL,
    cnpj VARCHAR(32) NOT NULL,
    logradouro VARCHAR(64),
    municipio VARCHAR(64),
    numero VARCHAR(10),
    complemento VARCHAR(64),
    bairro VARCHAR(64),
    cep VARCHAR(16),
    telefone VARCHAR(32),
    email VARCHAR(254),
    site VARCHAR(254),
    usuario VARCHAR(20) NOT NULL,
    senha VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT empresa_pk PRIMARY KEY (id),
    CONSTRAINT empresa_cnpj_uk UNIQUE (cnpj),
    CONSTRAINT empresa_usuario_uk UNIQUE (usuario)
);

CREATE TABLE unidade (
    id INTEGER NOT NULL DEFAULT nextval('seq_unidade'),
    nome VARCHAR(128) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unidade_pk PRIMARY KEY (id)
);

CREATE TABLE leilao (
    id INTEGER NOT NULL DEFAULT nextval('seq_leilao'),
    codigo INTEGER,
    descricao VARCHAR(60) NOT NULL,
    vendedor INTEGER NOT NULL,
    inicio_previsto TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT leilao_pk PRIMARY KEY (id),
    CONSTRAINT leilao_vendedor_fk FOREIGN KEY (vendedor) REFERENCES empresa (id)
);

CREATE TABLE lote (
    id INTEGER NOT NULL DEFAULT nextval('seq_lote'),
    numero_lote INTEGER,
    descricao VARCHAR(60) NOT NULL,
    quantidade NUMERIC(15, 2) NOT NULL,
    valor_inicial NUMERIC(15, 2),
    unidade VARCHAR(128) NOT NULL,
    leilao INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT lote_pk PRIMARY KEY (id),
    CONSTRAINT leilao_lote_fk FOREIGN KEY (leilao) REFERENCES leilao (id)
);

CREATE TABLE comprador (
    empresa INTEGER NOT NULL,
    leilao INTEGER NOT NULL,
    CONSTRAINT comprador_pk PRIMARY KEY (empresa, leilao),
    CONSTRAINT empresa_comp_fk FOREIGN KEY (empresa) REFERENCES empresa (id),
    CONSTRAINT leilao_comp_fk FOREIGN KEY (leilao) REFERENCES leilao (id)
);
