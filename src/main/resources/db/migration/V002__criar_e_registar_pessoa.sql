CREATE TABLE pessoa (
	codigo BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	ativo TINYINT,
	logradouro VARCHAR(60) NOT NULL,
	numero VARCHAR(6) NOT NULL,
	complemento VARCHAR(100),   
	bairro VARCHAR(60) NOT NULL,
	cep VARCHAR(20) NOT NULL,
	cidade VARCHAR(30) NOT NULL,
	estado VARCHAR(30) NOT NULL  
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa 
(nome,ativo,logradouro,numero,bairro,cep,cidade,estado)
VALUES
('Carlos Rafael Novaes',TRUE,'Rua Monsenhor Callou','234'
,'São José','55295290','Garanhuns','PE');

INSERT INTO pessoa 
(nome,ativo,logradouro,numero,bairro,cep,cidade,estado)
VALUES
('Mário Ryan Baptista',FALSE,'Rua Chácara Figueira','851'
,'Praia de Itaparica','29102027','Vila Velha','ES'); 

INSERT INTO pessoa 
(nome,logradouro,numero,bairro,cep,cidade,estado)
VALUES
('Vera Nina Carvalho','Rua da União','783'
,'Vitória','69901749','Rio Branco','AC');  