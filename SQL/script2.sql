-- Database: vetcare_db (INSERT SCRIPT)

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

------------------------------------------------------------------------------------

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
('12345678901', 'João da Silva', 'Rua A, 123', '(11) 98765-4321'),
('23456789012', 'Maria Souza', 'Avenida B, 456', '(11) 98876-5432'),
('34567890123', 'Carlos Pereira', 'Rua C, 789', '(11) 98987-6543'),
('45678901234', 'Fernanda Lima', 'Rua D, 101', '(11) 99101-7654');

-- Inserir animais
INSERT INTO Animal (id, nome, raca, peso, fk_Cliente_cpf, fk_tipo) VALUES
(1, 'Rex', 'Labrador', 30.5, '12345678901', 'Cão'),
(2, 'Mia', 'Persa', 5.0, '23456789012', 'Gato'),
(3, 'Bilu', 'Mini Coelho', 1.2, '34567890123', 'Coelho'),
(4, 'Harry', 'Siamês', 4.3, '45678901234', 'Gato'),
(5, 'Tommy', 'Sírio', 22.1, '12345678901', 'Hamster'),
(6, 'Luna', 'Rex', 8.5, '23456789012', 'Porquinho-da-Índia'),
(7, 'Kiko', 'Verdadeiro', 1.5, '34567890123', 'Papagaio'),
(8, 'Bel', 'Arlequim', 0.8, '45678901234', 'Calopsita'),
(9, 'Lia', 'Pintada', 8.5, '23456789012', 'Tartaruga'),
(10, 'Spike', 'Verde', 1.5, '34567890123', 'Iguana');

-------------------------------------------------------------- 

-- Inserir atendimentos

INSERT INTO Atendimento (data, id, fk_tipo) VALUES
('2024-11-15', 1, 'Consulta de rotina'),
('2024-11-16', 2, 'Vacinação'),
('2024-11-17', 3, 'Castração'),
('2024-11-18', 4, 'Procedimento Odontológico'),
('2024-11-19', 5, 'Exames laboratoriais'),
('2024-11-20', 6, 'Exames de imagem'),
('2024-11-21', 7, 'Enfermaria'),
('2024-11-22', 8, 'Desparasitação'),
('2024-11-23', 9, 'Cirurgia'),
('2024-11-24', 10, 'Atendimento de emergência'),
('2024-11-25', 11, 'Internação'),
('2024-11-26', 12, 'Compra'),
('2024-11-1', 13, 'Vacinação'),
('2024-11-2', 14, 'Vacinação'),
('2024-11-3', 15, 'Vacinação'),
('2024-11-4', 16, 'Vacinação'),
('2024-11-5', 17, 'Vacinação'),
('2024-11-6', 18, 'Vacinação');


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
('CRMV1', 11, 1),  
('CRMV1', 12, 2),
('CRMV4', 13, 1),
('CRMV1', 14, 2),
('CRMV1', 15, 3),
('CRMV1', 16, 1),
('CRMV1', 17, 5),
('CRMV1', 18, 6); 

INSERT INTO VacinaPet(fk_idVacina, fk_Atendimento_id) VALUES
('Leucemia Felina', 2),
('Antirrábica', 13),
('V10', 14),
('Giardia', 15),
('Gripe Canina', 16),
('V3', 17),
('V4', 18);

--------------------------------------------------------------------------------------------

-- Inserir insumos
INSERT INTO Insumo (nome, ean, preco, fk_tipo, quantidade) VALUES
('Papel A4', '1234567890123', 10.99, 'Administrativo', 100),
('Sabão em pó', '2345678901234', 8.50, 'Limpeza', 200),
('Coleira para Cão', '3456789012345', 35.75, 'Acessórios para Pets', 50),
('Ração para Cão', '4567890123456', 120.00, 'Alimentos para Pets', 300),
('Shampoo para Pets', '5678901234567', 25.50, 'Higiene para Pets', 80),
('Exame de sangue', '6789012345678', 150.00, 'Exames e Diagnósticos', 20),
('Antibiótico para Cão', '7890123456789', 45.00, 'Médicos', 60);

-- Inserir fornecedores
INSERT INTO Fornecedor (cnpj, contato, endereco, nome) VALUES
('12345678000100', 'Carlos Silva', 'Rua X, 123', 'Fornecedor A'),
('23456789000199', 'Maria Oliveira', 'Avenida Y, 456', 'Fornecedor B'),
('34567890000255', 'Fernanda Costa', 'Rua Z, 789', 'Fornecedor C');

-- Inserir fornecimento de insumos
INSERT INTO Fornecimento (quantidade, preco, fk_Fornecedor_cnpj, fk_Insumo_ean) VALUES
(50, 12.00, '12345678000100', '1234567890123'),  -- Fornecedor A fornece Papel A4
(100, 7.50, '23456789000199', '2345678901234'),  -- Fornecedor B fornece Sabão em pó
(30, 40.00, '34567890000255', '3456789012345'),  -- Fornecedor C fornece Coleira para Cão
(200, 110.00, '12345678000100', '4567890123456'),  -- Fornecedor A fornece Ração para Cão
(40, 22.00, '23456789000199', '5678901234567'),  -- Fornecedor B fornece Shampoo para Pets
(15, 140.00, '34567890000255', '6789012345678'),  -- Fornecedor C fornece Exame de sangue
(50, 40.00, '12345678000100', '7890123456789');  -- Fornecedor A fornece Antibiótico para Cão

---------------------------------------------------------------------------------------------------------

-- Inserir faturas
INSERT INTO Fatura (valor, data, fk_formaPagamento, id, fk_Cliente_cpf) VALUES
(200.50, '2024-11-15', 'Cartão de Crédito', 1, '12345678901'),
(150.75, '2024-11-16', 'Dinheiro', 2, '23456789012'),
(100.00, '2024-11-17', 'Pix', 3, '34567890123'),
(250.00, '2024-11-18', 'Cartão de Débito', 4, '45678901234');

-- Inserir itens de fatura
INSERT INTO Item_Fatura (quantidade, id, fk_idFatura, fk_Insumo_ean) VALUES
(2, 1, 1, '1234567890123'),  -- 2 Papel A4 na fatura 1
(1, 2, 2, '2345678901234'),  -- 1 Sabão em pó na fatura 2
(5, 3, 3, '3456789012345'),  -- 5 Coleiras para Cão na fatura 3
(3, 4, 4, '4567890123456');  -- 3 Rações para Cão na fatura 4

-- Inserir faturas relacionadas aos atendimentos
INSERT INTO AtendimentosFaturas (fk_Atendimento_id, fk_Fatura_id) VALUES
(1, 1),  -- Atendimento 1 relacionado à fatura 1
(2, 2),  -- Atendimento 2 relacionado à fatura 2
(3, 3),  -- Atendimento 3 relacionado à fatura 3
(4, 4);  -- Atendimento 4 relacionado à fatura 4