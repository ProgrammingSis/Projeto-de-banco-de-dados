# projeto-bd

<h1> Um trabalho de planejamento e criação de um banco de dados </h1>

# Justificativa:
  A criação de um banco de dados para uma clínica veterinária é essencial para organizar e centralizar informações dos pacientes (animais) e seus tutores, além de ser um fator facilitador para a criação de clientes de longo prazo na clínica. VetCare tem especificamente essa proposta, auxiliando  na gestão do atendimento e melhoria da qualidade dos serviços, permitindo que funcionários obtenham rapidamente informações importantes para o negócio. 
  
  Com esse sistema, também é possível fazer  controle financeiro, de estoque de insumos (com aba de avisos de validade próxima do vencimento), emails de aviso de consulta para clientes e médicos, além de emails para aviso de renovação de vacinas de pets, contribuindo para a eficiência da clínica ao analisar os registros gerados pelo banco de dados.

  Todas essas características visam agregar valor à clínica veterinária ao diminuir os custos de transação, facilitando processos repetitivos, visando o foco em tarefas mais importantes para o negócio.

## Passo a passo do projeto:

### Definição de Requisitos:
  1- Listagem de todas as informações que precisam ser armazenadas, como dados dos clientes (tutores), informações dos animais, consultas, tratamentos, vacinas, funcionários e fornecedores.
  
  2- Identificação das funcionalidades desejadas, como agendamento de consultas, histórico de tratamentos, controle de estoque de medicamentos e relatórios financeiros.
  
### Modelagem do Banco de Dados:

  3 - Definição do minimundo representando as principais entidades e relações do sistema.
  
  4 - Elaboração de um diagrama ER (entidade-relacionamento) para visualizar as entidades, atributos e relações, como a relação “atendido por” que conecta “Animais” com “Veterinários”.
  
  5 - Elaboração de um esquema de banco de dados.

### Elaboração do back-end e front-end

  6 - Elaboração da API do back-end usando Java e as dependências em pom.xml.
 
  7 - Elaboração das primeiras telas do front-end e conexão com um stylesheets de CSS.
 
  8 - Criação das telas e botões do front-end usando arquivos .fxml junto de arquivos de JavaFX (que foi escolhido para facilitar a integração)
  
  9 - Conexão entre APIs do back-end com botões e áreas certas do front-end
  
## Ferramentas e construção do banco de dados:

   Ferramenta usada para a construção do banco de dados: Postgres.

   - Todas as dependências usadas estão contidas no arquivo pom.xml. Foi usado o Spring Boot, Lombok, entre outros (como as bibliotecas do JavaFX).

# Como rodar?
    - Para rodar o projeto com facilidade, o IntelliJ oferece todo o suporte, para que apenas um botão seja suficiente para fazer a aplicação funcionar ao abrir a classe main. 

    - Tenha certeza de estar na pasta projeto-bd!!!

    - Para rodar o projeto, basta executar a classe Main que está no pacote na pasta `src/main/vetcare
  
  9 - Testes e Validação - serão feitos testes no banco de dados para verificar sua integridade.
  
# Implementação de Interface Gráfica:

<h2> Usando JavaFX, escolhemos fazer uma solução desktop que fosse de fácil integração com Java. </h2