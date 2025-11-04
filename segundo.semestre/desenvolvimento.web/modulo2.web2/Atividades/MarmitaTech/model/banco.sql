CREATE DATABASE IF NOT EXISTS MarmitaTech;
USE MarmitaTech;

CREATE TABLE IF NOT EXISTS avaliacao (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  estrelas INT NOT NULL,
  comentario TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  senha VARCHAR(255) NOT NULL,
  foto VARCHAR(255),
  nivel ENUM('admin', 'usuario') NOT NULL DEFAULT 'usuario'
);

CREATE TABLE IF NOT EXISTS servico (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(100) NOT NULL,
  descricao TEXT NOT NULL,
  preco DECIMAL(10,2) NOT NULL,
  foto VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS contato (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  mensagem TEXT NOT NULL,
  data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO servico (titulo, descricao, preco, foto) VALUES
('Marmita Família Brasileira', 'Marmita generosa para compartilhar, com pratos típicos da culinária brasileira.', 45.00, 'images/produtos/MarmitaFamiliaBrasileira.png'),
('Marmita Fit', 'Marmita saudável e nutritiva, com proteína magra, legumes e grãos integrais.', 28.50, 'images/produtos/MarmitaFit.png'),
('Marmita Tradicional', 'Marmita clássica com arroz, feijão, carne ou frango e acompanhamento de legumes.', 22.00, 'images/produtos/MarmitaTradicional.png'),
('Marmita Vegana', 'Marmita 100% vegana, com legumes, grãos, tofu e temperos naturais.', 27.00, 'images/produtos/MarmitaVegano.png');


INSERT INTO contato (nome, email, mensagem) VALUES
('João Silva', 'joao.silva@email.com', 'Gostei muito do serviço de marmitas, excelente qualidade!'),
('Maria Oliveira', 'maria.oliveira@email.com', 'Poderiam incluir opções vegetarianas no cardápio?');


INSERT INTO avaliacao (nome, estrelas, comentario) VALUES
('Carlos Souza', 5, 'Excelente atendimento e marmitas deliciosas!'),
('Ana Pereira', 4, 'Ótima variedade de pratos, recomendo para o almoço do trabalho.');

