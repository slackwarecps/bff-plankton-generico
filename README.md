# BFF Plankton Genérico

Este projeto é um Backend for Frontend (BFF) genérico construído com Spring Boot.

## Requisitos Funcionais

- **Endpoint Principal**: `/v1/planos`
- **Métodos Suportados**:
  - `GET /v1/planos`: Retorna uma lista de tarefas (Mock).
  - `GET /v1/planos/{id}`: Retorna uma tarefa específica por ID (Mock).
  - `POST /v1/planos`: Cria uma nova tarefa (Mock).
  - `PUT /v1/planos/{id}`: Atualiza uma tarefa existente (Mock).
  - `PATCH /v1/planos/{id}`: Atualiza parcialmente uma tarefa (Mock).
  - `DELETE /v1/planos/{id}`: Remove uma tarefa (Mock).
- **Domínio de Retorno**: Objeto `Tarefa` contendo:
  - `id` (Long)
  - `descricao` (String)

## Tecnologias

- Java 17
- Spring Boot 4.0.2
- Maven

## Como Executar

Para executar o projeto com as configurações padrão:

```bash
./mvnw spring-boot:run
```

### Perfil Local

Foi configurado um perfil local para desenvolvimento que utiliza a porta **7080**. Para executar usando este perfil:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Ou, se estiver usando o jar diretamente:

```bash
java -jar target/bff-plankton-generico-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```
