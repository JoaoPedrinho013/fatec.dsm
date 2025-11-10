CREATE TABLE Clientes (
    ID_Cliente NUMBER PRIMARY KEY,
    Nome VARCHAR2(100) NOT NULL,
    Endereco VARCHAR2(200),
    Telefone VARCHAR2(20),
    Email VARCHAR2(100),
    Documento VARCHAR2(20) UNIQUE
);

CREATE TABLE Destinos (
    ID_Destino NUMBER PRIMARY KEY,
    Nome_Destino VARCHAR2(100) NOT NULL,
    Pais VARCHAR2(50),
    Descricao VARCHAR2(300)
);

CREATE TABLE Pacotes (
    ID_Pacote NUMBER PRIMARY KEY,
    ID_Destino NUMBER NOT NULL,
    Nome_Pacote VARCHAR2(150),
    Preco_Por_Pessoa NUMBER(10,2),
    Data_Inicio DATE,
    Data_Fim DATE,
    CONSTRAINT fk_Pacote_Destino FOREIGN KEY (ID_Destino) REFERENCES Destinos(ID_Destino)
);

CREATE TABLE Reservas (
    ID_Reserva NUMBER PRIMARY KEY,
    ID_Cliente NUMBER NOT NULL,
    ID_Pacote NUMBER NOT NULL,
    Numero_Pessoas NUMBER,
    Data_Reserva DATE,
    Valor_Total NUMBER(10,2),
    CONSTRAINT fk_Reserva_Cliente FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente),
    CONSTRAINT fk_Reserva_Pacote FOREIGN KEY (ID_Pacote) REFERENCES Pacotes(ID_Pacote)
);

CREATE TABLE Pagamentos (
    ID_Pagamento NUMBER PRIMARY KEY,
    ID_Reserva NUMBER NOT NULL,
    Data_Pagamento DATE,
    Valor_Pagamento NUMBER(10,2),
    Forma_Pagamento VARCHAR2(50),
    CONSTRAINT fk_Pagamento_Reserva FOREIGN KEY (ID_Reserva) REFERENCES Reservas(ID_Reserva)
);

-- INSERÇÃO DE DADOS ----------------------------------------------

-- CLIENTES
INSERT INTO Clientes VALUES (1,'Ana Clara','Rua das Flores, 10','11987654321','ana@email.com','12345678900');
INSERT INTO Clientes VALUES (2,'Bruno Lima','Av. Paulista, 500','11977773333','bruno@email.com','23456789011');
INSERT INTO Clientes VALUES (3,'Carla Souza','Rua Verde, 25','21988884444','carla@email.com','34567890122');
INSERT INTO Clientes VALUES (4,'Diego Santos','Rua Azul, 32','31999995555','diego@email.com','45678901233');
INSERT INTO Clientes VALUES (5,'Eduarda Mendes','Av. Atlântica, 101','11911112222','eduarda@email.com','56789012344');
INSERT INTO Clientes VALUES (6,'Felipe Costa','Rua Aroeira, 87','41922223333','felipe@email.com','67890123455');
INSERT INTO Clientes VALUES (7,'Gabriela Nunes','Rua Pinho, 12','51933334444','gabi@email.com','78901234566');
INSERT INTO Clientes VALUES (8,'Henrique Alves','Av. Mar, 200','21944445555','henrique@email.com','89012345677');
INSERT INTO Clientes VALUES (9,'Isabela Rocha','Rua Central, 123','31955556666','isa@email.com','90123456788');
INSERT INTO Clientes VALUES (10,'João Pedro','Rua Bela Vista, 45','41966667777','joao@email.com','01234567899');

-- DESTINOS
INSERT INTO Destinos VALUES (1,'Paris','França','Cidade luz e romantismo');
INSERT INTO Destinos VALUES (2,'Tóquio','Japão','Cultura e tecnologia');
INSERT INTO Destinos VALUES (3,'Roma','Itália','História e gastronomia');
INSERT INTO Destinos VALUES (4,'Cancún','México','Praias e resorts');
INSERT INTO Destinos VALUES (5,'Lisboa','Portugal','História e charme');
INSERT INTO Destinos VALUES (6,'Buenos Aires','Argentina','Tango e culinária');
INSERT INTO Destinos VALUES (7,'Nova York','EUA','Compras e entretenimento');
INSERT INTO Destinos VALUES (8,'Dubai','Emirados Árabes','Luxo e inovação');
INSERT INTO Destinos VALUES (9,'Machu Picchu','Peru','Ruínas incas e aventura');
INSERT INTO Destinos VALUES (10,'Rio de Janeiro','Brasil','Praias e natureza');

-- PACOTES
INSERT INTO Pacotes VALUES (1,1,'Paris Romântica',6500,TO_DATE('10-Dec-2023','DD-Mon-YYYY'),TO_DATE('17-Dec-2023','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (2,2,'Tóquio Cultural',8700,TO_DATE('15-Jan-2024','DD-Mon-YYYY'),TO_DATE('25-Jan-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (3,3,'Roma Histórica',7200,TO_DATE('01-Feb-2024','DD-Mon-YYYY'),TO_DATE('09-Feb-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (4,4,'Cancún All-Inclusive',9500,TO_DATE('10-Mar-2024','DD-Mon-YYYY'),TO_DATE('18-Mar-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (5,5,'Lisboa Encantadora',5800,TO_DATE('05-Apr-2024','DD-Mon-YYYY'),TO_DATE('12-Apr-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (6,6,'Buenos Aires & Tango',4300,TO_DATE('20-Apr-2024','DD-Mon-YYYY'),TO_DATE('26-Apr-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (7,7,'Nova York Express',8900,TO_DATE('10-May-2024','DD-Mon-YYYY'),TO_DATE('17-May-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (8,8,'Dubai Luxo',12000,TO_DATE('01-Jun-2024','DD-Mon-YYYY'),TO_DATE('08-Jun-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (9,9,'Aventura Inca',9100,TO_DATE('15-Jul-2024','DD-Mon-YYYY'),TO_DATE('22-Jul-2024','DD-Mon-YYYY'));
INSERT INTO Pacotes VALUES (10,10,'Rio Maravilha',4000,TO_DATE('05-Aug-2024','DD-Mon-YYYY'),TO_DATE('10-Aug-2024','DD-Mon-YYYY'));

-- RESERVAS
INSERT INTO Reservas VALUES (1,1,1,2,TO_DATE('20-Oct-2023','DD-Mon-YYYY'),13000);
INSERT INTO Reservas VALUES (2,2,2,1,TO_DATE('05-Nov-2023','DD-Mon-YYYY'),8700);
INSERT INTO Reservas VALUES (3,3,3,3,TO_DATE('10-Nov-2023','DD-Mon-YYYY'),21600);
INSERT INTO Reservas VALUES (4,4,4,1,TO_DATE('15-Nov-2023','DD-Mon-YYYY'),9500);
INSERT INTO Reservas VALUES (5,5,5,2,TO_DATE('20-Nov-2023','DD-Mon-YYYY'),11600);
INSERT INTO Reservas VALUES (6,6,6,1,TO_DATE('22-Nov-2023','DD-Mon-YYYY'),4300);
INSERT INTO Reservas VALUES (7,7,7,2,TO_DATE('25-Nov-2023','DD-Mon-YYYY'),17800);
INSERT INTO Reservas VALUES (8,8,8,1,TO_DATE('28-Nov-2023','DD-Mon-YYYY'),12000);
INSERT INTO Reservas VALUES (9,9,9,1,TO_DATE('01-Dec-2023','DD-Mon-YYYY'),9100);
INSERT INTO Reservas VALUES (10,10,10,4,TO_DATE('05-Dec-2023','DD-Mon-YYYY'),16000);

-- PAGAMENTOS
INSERT INTO Pagamentos VALUES (1,1,TO_DATE('20-Oct-2023','DD-Mon-YYYY'),6500,'Cartão de Crédito');
INSERT INTO Pagamentos VALUES (2,1,TO_DATE('25-Oct-2023','DD-Mon-YYYY'),6500,'Cartão de Crédito');
INSERT INTO Pagamentos VALUES (3,2,TO_DATE('05-Nov-2023','DD-Mon-YYYY'),8700,'Pix');
INSERT INTO Pagamentos VALUES (4,3,TO_DATE('12-Nov-2023','DD-Mon-YYYY'),10800,'Boleto');
INSERT INTO Pagamentos VALUES (5,3,TO_DATE('15-Nov-2023','DD-Mon-YYYY'),10800,'Cartão');
INSERT INTO Pagamentos VALUES (6,4,TO_DATE('15-Nov-2023','DD-Mon-YYYY'),9500,'Cartão de Débito');
INSERT INTO Pagamentos VALUES (7,5,TO_DATE('21-Nov-2023','DD-Mon-YYYY'),11600,'Transferência');
INSERT INTO Pagamentos VALUES (8,6,TO_DATE('22-Nov-2023','DD-Mon-YYYY'),4300,'Pix');
INSERT INTO Pagamentos VALUES (9,7,TO_DATE('26-Nov-2023','DD-Mon-YYYY'),17800,'Cartão de Crédito');
INSERT INTO Pagamentos VALUES (10,8,TO_DATE('28-Nov-2023','DD-Mon-YYYY'),12000,'Cartão de Débito');

COMMIT;