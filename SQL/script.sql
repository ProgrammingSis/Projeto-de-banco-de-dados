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
    id INTEGER PRIMARY KEY,
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
    id INTEGER PRIMARY KEY,
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
    ean CHAR(13) PRIMARY KEY,
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
    id SERIAL PRIMARY KEY,
    quantidade INTEGER,
    preco DECIMAL(10,2),
    fk_Fornecedor_cnpj CHAR(14),
    fk_Insumo_ean CHAR(13)
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
    id INTEGER PRIMARY KEY,
    fk_Cliente_cpf CHAR(11),
    CONSTRAINT fk_formaPagamento FOREIGN KEY (fk_formaPagamento) REFERENCES FormaPagamento(nome)
);

CREATE TABLE Item_Fatura ( -- Uma tupla dessa tabela representa 1 item de uma fatura (1 Fatura para N Item_Fatura)
    quantidade INTEGER,
    id INTEGER PRIMARY KEY,
    fk_idFatura INTEGER,
    fk_Insumo_ean CHAR(13),
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