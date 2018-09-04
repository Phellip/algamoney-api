CREATE TABLE lancamento(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao,data_vencimento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Bolacha','2018-08-10',3.20,'DESPESA',2,4); 
INSERT INTO lancamento (descricao,data_vencimento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Compra','2018-08-20',1200.84,'DESPESA',3,4); 


