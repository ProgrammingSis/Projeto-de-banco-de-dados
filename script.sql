-- Database: vetcare_db2

-- DROP DATABASE IF EXISTS vetcare_db2;

-- CREATE DATABASE vetcare_db2
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'Portuguese_Brazil.1252'
--     LC_CTYPE = 'Portuguese_Brazil.1252'
--     LOCALE_PROVIDER = 'libc'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1
--     IS_TEMPLATE = False;

	-- Database: vetcare_db

CREATE TABLE Especialidade (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Veterinario (
    crmv VARCHAR(10) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    fk_especialidade VARCHAR(40) NOT NULL,
    CONSTRAINT fk_especialidade FOREIGN KEY (fk_especialidade) REFERENCES Especialidade(nome)
);

ALTER TABLE Veterinario ADD COLUMN contato VARCHAR(40);

CREATE TABLE Cliente (
    cpf CHAR(11) PRIMARY KEY,
    nome VARCHAR(60),
    endereco VARCHAR(80),
    contato VARCHAR(50)
);

CREATE TABLE TipoAnimal (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Animal (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(20),
    raca VARCHAR(20),
    peso DECIMAL(5,2),
    fk_Cliente_cpf CHAR(11),
    fk_tipo VARCHAR(40) NOT NULL,
    CONSTRAINT fk_tipo FOREIGN KEY (fk_tipo) REFERENCES TipoAnimal(nome)
);

CREATE TABLE TipoAtendimento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Atendimento ( 
    data DATE,
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fk_tipo VARCHAR(40) NOT NULL,
    CONSTRAINT fk_tipo FOREIGN KEY (fk_tipo) REFERENCES TipoAtendimento(nome)
);

ALTER TABLE Atendimento ADD COLUMN horario TIME;

CREATE TABLE AtendidoEm ( 
    fk_Veterinario_crmv VARCHAR(10),
    fk_Atendimento_id INTEGER PRIMARY KEY,
    fk_Animal_id INTEGER
);

CREATE TABLE TipoVacina (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE VacinaPet (
    fk_idVacina VARCHAR(40),
    fk_Atendimento_id INTEGER,
    CONSTRAINT fk_idVacina FOREIGN KEY (fk_idVacina) REFERENCES TipoVacina(nome),
    CONSTRAINT fk_Atendimento_id FOREIGN KEY (fk_Atendimento_id) REFERENCES Atendimento(id)
);

---------------------------------------------------

CREATE TABLE TipoInsumo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Insumo (
    nome VARCHAR(50),
    data DATE, -- Data de vencimento do Insumo
    ean INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    preco DECIMAL(10,2),
    fk_tipo VARCHAR(40) NOT NULL,
    quantidade INTEGER,
    CONSTRAINT fk_tipo FOREIGN KEY (fk_tipo) REFERENCES TipoInsumo(nome)
);

CREATE TABLE Fornecedor (
    cnpj CHAR(14) PRIMARY KEY,
    contato VARCHAR(50),
    endereco VARCHAR(80),
    nome VARCHAR(50)
);

CREATE TABLE Fornecimento ( --  Compra !! 
    id INTEGER  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    quantidade INTEGER,
    preco DECIMAL(10,2),
    fk_Fornecedor_cnpj CHAR(14),
    fk_Insumo_ean INTEGER
);

----------------------------------------------------

CREATE TABLE FormaPagamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE Fatura (
    valor DECIMAL(10,2),
    data DATE,
    fk_formaPagamento VARCHAR(40) NOT NULL,
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fk_Cliente_cpf CHAR(11),
    CONSTRAINT fk_formaPagamento FOREIGN KEY (fk_formaPagamento) REFERENCES FormaPagamento(nome)
);

CREATE TABLE Item_Fatura ( -- Uma tupla dessa tabela representa 1 item de uma fatura (1 Fatura para N Item_Fatura)
    quantidade INTEGER,
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fk_idFatura INTEGER,
    fk_Insumo_ean INTEGER,
    CONSTRAINT fk_idFatura FOREIGN KEY (fk_idFatura) REFERENCES Fatura(id)
);

CREATE TABLE AtendimentosFaturas (
    fk_Atendimento_id INTEGER, -- Se foi atendido
    fk_Fatura_id INTEGER PRIMARY KEY -- id da Fatura
);

-----------------------------------------------------

ALTER TABLE Animal ADD CONSTRAINT FK_Animal_2
    FOREIGN KEY (fk_Cliente_cpf)
    REFERENCES Cliente (cpf);

ALTER TABLE Item_Fatura ADD CONSTRAINT FK_Item_Fatura_2
    FOREIGN KEY (fk_Insumo_ean)
    REFERENCES Insumo (ean);
 
ALTER TABLE Fatura ADD CONSTRAINT FK_Fatura_2
    FOREIGN KEY (fk_Cliente_cpf)
    REFERENCES Cliente (cpf);
 
ALTER TABLE Fornecimento ADD CONSTRAINT FK_Fornece_1
    FOREIGN KEY (fk_Fornecedor_cnpj)
    REFERENCES Fornecedor (cnpj);
 
ALTER TABLE Fornecimento ADD CONSTRAINT FK_Fornece_2
    FOREIGN KEY (fk_Insumo_ean)
    REFERENCES Insumo (ean);
 
ALTER TABLE AtendidoEm ADD CONSTRAINT FK_AtendidoEm_1
    FOREIGN KEY (fk_Veterinario_crmv)
    REFERENCES Veterinario (crmv);
 
ALTER TABLE AtendidoEm ADD CONSTRAINT FK_AtendidoEm_2
    FOREIGN KEY (fk_Atendimento_id)
    REFERENCES Atendimento (id);
 
ALTER TABLE AtendidoEm ADD CONSTRAINT FK_AtendidoEm_3
    FOREIGN KEY (fk_Animal_id)
    REFERENCES Animal (id);
	
ALTER TABLE AtendimentosFaturas ADD CONSTRAINT FK_AtendimentosFaturas_2
    FOREIGN KEY (fk_Atendimento_id)
    REFERENCES Atendimento (id);

ALTER TABLE AtendimentosFaturas ADD CONSTRAINT FK_AtendimentosFaturas_3
	FOREIGN KEY (fk_Fatura_id) REFERENCES Fatura(id);

-----------------------------------------------------------------------------------------

-- PARTE 2: INSERÇÃO DE DADOS FICTÍCIOS DO DOMÍNIO:uma tabela por vez !

INSERT INTO Especialidade (nome) VALUES 
('Clínica Geral'),
('Cirurgia Geral'),
('Dermatologia'),
('Anestesiologia'),
('Cardiologia'),
('Odontologia'),
('Ortopedia'),
('Radiologia Veterinária');

INSERT INTO TipoAnimal (nome) VALUES
('Cão'),
('Gato'),
('Coelho'),
('Hamster'),
('Porquinho-da-Índia'),
('Papagaio'),
('Calopsita'),
('Tartaruga'),
('Iguana');

INSERT INTO TipoAtendimento (nome) VALUES
('Consulta de rotina'),
('Vacinação'),
('Castração'),
('Procedimento Odontológico'),
('Exames laboratoriais'),
('Exames de imagem'),
('Enfermaria'),
('Desparasitação'),
('Cirurgia'),
('Atendimento de emergência'),
('Internação'),
('Compra');

INSERT INTO TipoVacina (nome) VALUES
('Antirrábica'),
('V10'),
('Giardia'),
('Gripe Canina'),
('V3'),
('V4'),
('Leucemia Felina');

INSERT INTO TipoInsumo (nome) VALUES
('Administrativo'),
('Limpeza'),
('Acessórios para Pets'),
('Alimentos para Pets'),
('Higiene para Pets'),
('Exames e Diagnósticos'),
('Médicos');

INSERT INTO FormaPagamento (nome) VALUES
('Cartão de Crédito'),
('Cartão de Débito'),
('Dinheiro'),
('Pix');

-------------------------------------------------------------------------------

-- Inserir veterinários
INSERT INTO Veterinario (crmv, nome, fk_especialidade) VALUES
('CRMV1', 'Dr. João Silva', 'Clínica Geral'),
('CRMV2', 'Dra. Maria Oliveira', 'Cirurgia Geral'),
('CRMV3', 'Dr. Carlos Souza', 'Dermatologia'),
('CRMV4', 'Dra. Fernanda Lima', 'Anestesiologia'),
('CRMV5', 'Dr. Pedro Costa', 'Cardiologia'),
('CRMV6', 'Dra. Juliana Pereira', 'Odontologia'),
('CRMV7', 'Dr. Lucas Gomes', 'Ortopedia'),
('CRMV8', 'Dra. Helena Martins', 'Radiologia Veterinária');

-- Inserir clientes
INSERT INTO Cliente (cpf, nome, endereco, contato) VALUES
('35443006053', 'André Morales', 'Rua A, 123', 'andremorales@usp.br'),
('78109512038', 'Ana Paula Bernardo', 'Avenida B, 456', 'anapaula.santos@usp.br'),
('77879740015', 'Ana Alice Padovan', 'Rua C, 789', 'andremorales@usp.br'),
('21182368085', 'Sara Letícia', 'Rua D, 101', 'anapaula.santos@usp.br'),
('37774457038', 'João Pedro Barbosa', 'Rua D, 106', 'jprbarbosa16@usp.br'),
('59961390067', 'Daniel Coutinho', 'Rua D, 136', 'daniel_cr.11@usp.br');

-- Inserir animais
INSERT INTO Animal (nome, raca, peso, fk_Cliente_cpf, fk_tipo) VALUES
('Rex', 'Labrador', 30.5, '35443006053', 'Cão'),
('Mia', 'Persa', 5.0, '78109512038', 'Gato'),
('Bilu', 'Mini Coelho', 1.2, '77879740015', 'Coelho'),
('Harry', 'Siamês', 4.3, '21182368085', 'Gato'),
('Tommy', 'Sírio', 22.1, '35443006053', 'Hamster'),
('Luna', 'Rex', 8.5, '78109512038', 'Porquinho-da-Índia'),
('Kiko', 'Verdadeiro', 1.5, '77879740015', 'Papagaio'),
('Bel', 'Arlequim', 0.8, '21182368085', 'Calopsita'),
('Lia', 'Pintada', 8.5, '78109512038', 'Tartaruga'),
('Spike', 'Verde', 1.5, '77879740015', 'Iguana'),
('Corinthiano', 'Labrador Preto', 1.5, '37774457038', 'Cão'),
('Totó', 'Vira lata Caramelo', 1.5, '59961390067', 'Cão');

-------------------------------------------------------------- 

-- Inserir atendimentos

INSERT INTO Atendimento (data, fk_tipo) VALUES
('2024-11-15', 'Consulta de rotina'),
('2010-11-16', 'Vacinação'),
('2024-11-17', 'Castração'),
('2024-11-18', 'Procedimento Odontológico'),
('2024-11-19', 'Exames laboratoriais'),
('2024-11-20', 'Exames de imagem'),
('2024-11-21', 'Enfermaria'),
('2024-11-22', 'Desparasitação'),
('2024-11-23', 'Cirurgia'),
('2024-11-24', 'Atendimento de emergência'),
('2024-11-25', 'Internação'),
('2024-11-26', 'Compra');

INSERT INTO AtendidoEm (fk_Veterinario_crmv, fk_Atendimento_id, fk_Animal_id) VALUES
('CRMV1', 1, 1), 
('CRMV2', 2, 2),  
('CRMV3', 3, 3), 
('CRMV4', 4, 4),  
('CRMV5', 5, 5),  
('CRMV6', 6, 6),  
('CRMV7', 7, 7), 
('CRMV8', 8, 8),  
('CRMV2', 9, 9), 
('CRMV1', 10, 10),
('CRMV1', 11, 11),  
('CRMV1', 12, 12);

INSERT INTO VacinaPet(fk_idVacina, fk_Atendimento_id) VALUES
('Leucemia Felina', 2);

--------------------------------------------------------------------------------------------

-- Inserir insumos
INSERT INTO Insumo (nome, data, preco, fk_tipo, quantidade) VALUES
('Papel A4', '2025-11-23', 10.99, 'Administrativo', 100),
('Sabão em pó', '2026-11-23', 8.50, 'Limpeza', 200),
('Coleira para Cão', '2027-11-23',  35.75, 'Acessórios para Pets', 50),
('Ração para Cão', '2024-11-25', 120.00, 'Alimentos para Pets', 300),
('Shampoo para Pets', '2028-11-23', 25.50, 'Higiene para Pets', 80),
('Exame de sangue', '2020-11-23', 150.00, 'Exames e Diagnósticos', 20),
('Antibiótico para Cão', '2025-03-23', 45.00, 'Médicos', 60);

-- Inserir fornecedores
INSERT INTO Fornecedor (cnpj, contato, endereco, nome) VALUES
('823.141.910-13', 'Carlos Silva', 'Rua X, 123', 'Fornecedor A'),
('600.890.250-59', 'Maria Oliveira', 'Avenida Y, 456', 'Fornecedor B'),
('326.967.810-01', 'Fernanda Costa', 'Rua Z, 789', 'Fornecedor C');

-- Inserir fornecimento de insumos
INSERT INTO Fornecimento (quantidade, preco, fk_Fornecedor_cnpj, fk_Insumo_ean) VALUES
(50, 12.00, '823.141.910-13', '1'),  -- Fornecedor A fornece Papel A4
(100, 7.50, '600.890.250-59', '2'),  -- Fornecedor B fornece Sabão em pó
(30, 40.00, '326.967.810-01', '3'),  -- Fornecedor C fornece Coleira para Cão
(200, 110.00, '823.141.910-13', '4'),  -- Fornecedor A fornece Ração para Cão
(40, 22.00, '600.890.250-59', '5'),  -- Fornecedor B fornece Shampoo para Pets
(15, 140.00, '326.967.810-01', '6'),  -- Fornecedor C fornece Exame de sangue
(50, 40.00, '823.141.910-13', '7');  -- Fornecedor A fornece Antibiótico para Cão

---------------------------------------------------------------------------------------------------------

-- Inserir faturas
INSERT INTO Fatura (valor, data, fk_formaPagamento, fk_Cliente_cpf) VALUES
(200.50, '2024-11-15', 'Cartão de Crédito', '35443006053'),
(150.75, '2024-11-16', 'Dinheiro','78109512038'),
(100.00, '2024-11-17', 'Pix', '77879740015'),
(250.00, '2024-11-18', 'Cartão de Débito','21182368085');

-- Inserir itens de fatura
INSERT INTO Item_Fatura (quantidade, fk_idFatura, fk_Insumo_ean) VALUES
(2, 1, '001'),  -- 2 Papel A4 na fatura 1
(1, 2, '002'),  -- 1 Sabão em pó na fatura 2
(5, 3, '003'),  -- 5 Coleiras para Cão na fatura 3
(3, 4, '004');  -- 3 Rações para Cão na fatura 4

-- Inserir faturas relacionadas aos atendimentos
INSERT INTO AtendimentosFaturas (fk_Atendimento_id, fk_Fatura_id) VALUES
(1, 1),  -- Atendimento 1 relacionado à fatura 1
(2, 2),  -- Atendimento 2 relacionado à fatura 2
(3, 3),  -- Atendimento 3 relacionado à fatura 3
(4, 4);  -- Atendimento 4 relacionado à fatura 4