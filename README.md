# Daily Reports

**Descrição**  
O projeto *Daily Reports* é um sistema de back-end que permite a criação de relatórios diários no contexto de manutenção industrial. Ele serve para o acompanhamento da execução de ordens de serviço e controle de homem-hora, com integração com o Amazon S3 para o armazenamento de fotos e uso de MySQL como banco de dados.

Futuramente, o front-end será desenvolvido utilizando Angular.

---

## Tecnologias Usadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework utilizado para construção da aplicação back-end.
- **MapStruct**: Framework para mapeamento de objetos (DTOs).
- **Amazon S3**: Utilizado para o armazenamento de arquivos e dados relacionados aos relatórios.
- **MySQL**: Banco de dados utilizado para persistência dos dados.

---

## Estrutura do Repositório

O repositório contém os seguintes arquivos e pastas:

- `src/`: Pasta contendo o código-fonte em Java da solução.
- `test/`: Pasta contendo testes unitários para validar a solução (Ainda não implementados).
- `application.yml`: Arquivo de configuração da aplicação (incluindo configuração do banco de dados e Amazon S3).
