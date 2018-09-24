CREATE TABLE lancamento(
	codigo BIGINT(20) NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	INDEX index_categoria(codigo_categoria),
	INDEX index_pessoa(codigo_pessoa),
	CONSTRAINT pk_lancamento PRIMARY KEY(codigo),
    CONSTRAINT fk_categoria FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	CONSTRAINT fk_pessoa FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento 
	(descricao,data_vencimento,valor,tipo,codigo_categoria,codigo_pessoa)
VALUES
	('Remédios','2018-07-05',450.00,'DESPESA',4,1);
 
 INSERT INTO lancamento 
	(descricao,data_vencimento,valor,tipo,codigo_categoria,codigo_pessoa)
VALUES
	('Remédios','2018-07-05',1450.98,'DESPESA',4,2);
    
INSERT INTO lancamento 
	(descricao,data_vencimento,valor,tipo,codigo_categoria,codigo_pessoa)
VALUES
	('Remédios','2018-07-05',130.54,'DESPESA',4,3);