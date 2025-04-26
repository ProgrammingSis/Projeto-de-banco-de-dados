# Projeto BD: VetCare 🐾

Este repositório contém o projeto Sistema Gerenciador de Banco de Dados, um trabalho de planejamento e criação de um banco de dados para uma clínica veterinária, realizado na disciplina de Banco de Dados I - ACH200, da EACH-USP.

---

## 📋 Justificativa
A criação de um banco de dados para uma clínica veterinária é essencial para organizar e centralizar informações sobre os pacientes (animais) e seus tutores. Além disso, é um diferencial estratégico para fidelizar clientes de longo prazo. 

O sistema **VetCare** foi desenvolvido com esse propósito, oferecendo funcionalidades que auxiliam na gestão de atendimentos e na melhoria da qualidade dos serviços. Ele permite que os funcionários acessem rapidamente informações importantes, como:

- Controle financeiro;
- Gestão de estoque com alertas de validade próxima ao vencimento;
- Envio de e-mails de avisos de consulta para clientes e médicos;
- Notificações de renovação de vacinas dos pets.

Todas essas funcionalidades agregam valor à clínica ao reduzir custos operacionais, facilitar tarefas repetitivas e permitir que a equipe se concentre nas atividades mais importantes para o negócio.

---

## 🛠️ Passo a Passo do Projeto

### 1. Definição de Requisitos
- Listar todas as informações que precisam ser armazenadas: dados dos tutores, informações dos animais, consultas, tratamentos, vacinas, funcionários e fornecedores.
- Identificar as funcionalidades desejadas: agendamento de consultas, histórico de tratamentos, controle de estoque de medicamentos e geração de relatórios financeiros.

### 2. Modelagem do Banco de Dados
- Definir o **minimundo**, representando as principais entidades e relações do sistema.
- Elaborar o diagrama ER (entidade-relacionamento), destacando conexões como "Animais" sendo "atendidos por" "Veterinários".
- Criar o esquema do banco de dados.

### 3. Desenvolvimento do Back-End e Front-End
- Desenvolver a API do back-end em **Java**, utilizando dependências configuradas no `pom.xml`.
- Criar as primeiras telas do front-end, estilizadas com CSS.
- Implementar o front-end com arquivos `.fxml` e integração com **JavaFX**.
- Conectar as APIs do back-end às funcionalidades do front-end, como botões e áreas específicas da interface.

### 4. Testes e Validação
- Realizar testes no banco de dados para verificar a integridade e o funcionamento correto do sistema.

---

## 🧰 Ferramentas Utilizadas
- **Banco de Dados**: PostgreSQL
- **Back-End**: Java com **Spring Boot**, **Lombok** e outras dependências (listadas no `pom.xml`).
- **Front-End**: Optamos por uma solução desktop com **JavaFX**, que permite fácil integração com Java e oferece uma interface amigável e intuitiva para os usuários da clínica veterinária.

---

## 🚀 Como Rodar o Projeto?
1. Certifique-se de estar na pasta raiz do projeto: `projeto-bd`.
2. Abra o projeto no **IntelliJ IDEA** (ou qualquer IDE compatível).
3. Execute a classe `Main` localizada em `src/main/vetcare` para iniciar a aplicação.
4. Pronto! A aplicação estará rodando.

---

Com esse projeto, esperamos fornecer uma solução robusta e eficiente para as demandas da clínica veterinária, contribuindo para a melhoria de seus processos e serviços.
