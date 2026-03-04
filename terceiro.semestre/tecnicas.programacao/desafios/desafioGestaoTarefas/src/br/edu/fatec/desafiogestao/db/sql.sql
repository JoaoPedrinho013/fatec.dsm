CREATE DATABASE gestao_tarefas;

CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT,
    concluida BOOLEAN NOT NULL DEFAULT FALSE,
    categoria VARCHAR(100) NOT NULL
);