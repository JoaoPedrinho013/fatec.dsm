-- Geração de Modelo físico
-- Sql ANSI 2003 - brModelo.



CREATE TABLE PROFESSOR_COORDENADOR (
nome_professor_coordenador VARCHAR(45),
registro_professor_coordenador INTEGER PRIMARY KEY
)

CREATE TABLE PROFESSOR (
registro_professor INTEGER PRIMARY KEY,
nome_professor VARCHAR(45),
registro_professor_coordenador INTEGER,
FOREIGN KEY(registro_professor_coordenador) REFERENCES PROFESSOR_COORDENADOR (registro_professor_coordenador)
)

