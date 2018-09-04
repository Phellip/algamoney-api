CREATE TABLE categoria(
    codigo BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria(nome) VALUES("Lazer");
INSERT INTO categoria(nome) VALUES("Alimentação");
INSERT INTO categoria(nome) VALUES("Supermercado");
INSERT INTO categoria(nome) VALUES("Farmácias");
INSERT INTO categoria(nome) VALUES("Outros");