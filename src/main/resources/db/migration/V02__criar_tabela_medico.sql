CREATE TABLE medico(
	cpf BIGINT PRIMARY KEY,
	especialidade VARCHAR(255),
	nome VARCHAR(255),
	crm BIGINT,
	endereco VARCHAR(255),
	numero_casa INTEGER,
	bairro VARCHAR(255),
	data_nascimento DATE
);