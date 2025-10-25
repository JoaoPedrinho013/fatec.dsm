-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.



CREATE TABLE PROFESSOR (
registro INTEGER PRIMARY KEY,
nome VARCHAR(45)
)

CREATE TABLE Coordena (
registro INTEGER,
possui_registro INTEGER
)

