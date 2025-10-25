-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.



CREATE TABLE ESPECIALIDADE (
sigla CHAR(4) PRIMARY KEY,
descricao VARCHAR(45)
)

CREATE TABLE MEDICO (
crm INTEGER PRIMARY KEY,
nome_medico VARCHAR(45),
sigla CHAR(4),
FOREIGN KEY(sigla) REFERENCES ESPECIALIDADE (sigla)
)

CREATE TABLE PACIENTE (
cpf NUMERIC(11) PRIMARY KEY,
nome_paciente VARCHAR(45),
sexo CHAR(1),
telefone CHAR(15),
telefone1 CHAR(15),
telefone2 CHAR(15),
telefone3 CHAR(15),
telefone4 CHAR(15),
logradouro VARCHAR(50),
numero VARCHAR(10),
complemento VARCHAR(10),
cep VARCHAR(8),
bairro VARCHAR(12),
cidade VARCHAR(10),
uf CHAR(2)
)

CREATE TABLE ATENDIMENTO (
cpf NUMERIC(11),
crm INTEGER,
data DATETIME,
hora DATETIME,
valor_atendimento NUMERIC((10,2)),
PRIMARY KEY(cpf,crm,data,hora),
FOREIGN KEY(cpf) REFERENCES PACIENTE (cpf),
FOREIGN KEY(crm) REFERENCES MEDICO (crm)
)

