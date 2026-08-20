# Cadastro de Alunos — API REST

API REST desenvolvida com **Spring Boot 3**, **Spring Data JPA** e **PostgreSQL**, criada para a disciplina de Desenvolvimento de Aplicações Web (TP2).

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Spring Boot | 3.4.5 |
| Spring Data JPA | (gerenciado pelo Boot) |
| Hibernate | (gerenciado pelo Boot) |
| PostgreSQL | 14+ |
| Maven | 3.8+ |

## Endpoints

| Método | URL | Descrição | Status |
|---|---|---|---|
| `GET` | `/alunos` | Lista todos os alunos | 200 OK |
| `GET` | `/alunos/{id}` | Busca aluno por ID | 200 / 404 |
| `POST` | `/alunos` | Cadastra novo aluno | 201 Created |
| `PUT` | `/alunos/{id}` | Atualiza aluno existente | 200 / 404 |
| `DELETE` | `/alunos/{id}` | Remove aluno | 204 / 404 |

## Estrutura do Projeto

```
src/main/java/com/example/cadastroalunos/
├── CadastroAlunosApplication.java   # ponto de entrada
├── model/
│   └── Aluno.java                   # entidade JPA → tabela "alunos"
├── repository/
│   └── AlunoRepository.java         # JpaRepository (CRUD automático)
└── controller/
    └── AlunoController.java         # endpoints REST (@RestController)
```

## Configuração

### 1. Banco de dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE cadastro_alunos;
```

### 2. application.properties

Edite `src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cadastro_alunos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> O Hibernate cria a tabela `alunos` automaticamente no primeiro boot.

## Como executar

```bash
# Linux / Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## Testando com Postman

### GET — Listar alunos
```
GET http://localhost:8080/alunos
```

### POST — Cadastrar aluno
```
POST http://localhost:8080/alunos
Content-Type: application/json

{
    "nome": "Maria Silva",
    "cpf": "123.456.789-00",
    "email": "maria.silva@email.com"
}
```

### PUT — Atualizar aluno
```
PUT http://localhost:8080/alunos/1
Content-Type: application/json

{
    "nome": "Maria Silva Santos",
    "cpf": "123.456.789-00",
    "email": "maria.santos@email.com"
}
```

### DELETE — Remover aluno
```
DELETE http://localhost:8080/alunos/1
```

## Script SQL — Popular banco com dados de exemplo

```sql
INSERT INTO alunos (nome, cpf, email) VALUES
  ('Ana Souza',       '111.111.111-11', 'ana.souza@email.com'),
  ('Bruno Lima',      '222.222.222-22', 'bruno.lima@email.com'),
  ('Carla Mendes',    '333.333.333-33', 'carla.mendes@email.com'),
  ('Diego Ferreira',  '444.444.444-44', 'diego.ferreira@email.com'),
  ('Elisa Rodrigues', '555.555.555-55', 'elisa.rodrigues@email.com');
```

## Modelo de dados

```json
{
    "id":    1,
    "nome":  "Ana Souza",
    "cpf":   "111.111.111-11",
    "email": "ana.souza@email.com"
}
```

| Campo | Tipo | Restrição |
|---|---|---|
| id | BIGSERIAL | PK, auto-increment |
| nome | VARCHAR | NOT NULL |
| cpf | VARCHAR(14) | NOT NULL, UNIQUE |
| email | VARCHAR | NOT NULL, UNIQUE |
