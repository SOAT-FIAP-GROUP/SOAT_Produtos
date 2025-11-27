CREATE TABLE categorias (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NULL,
    PRIMARY KEY (codigo)
);

CREATE TABLE produtos (
    codigo BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NULL,
    descricao VARCHAR(255) NULL,
    categoriacodigo BIGINT NULL,
    preco DECIMAL(10,2) NULL,
    tempopreparo TIME NULL,
    PRIMARY KEY (codigo)
);

-- Foreign Keys

ALTER TABLE produtos
    ADD CONSTRAINT FK_PRODUTOS_ON_CATEGORIACODIGO FOREIGN KEY (categoriacodigo) REFERENCES categorias (codigo);