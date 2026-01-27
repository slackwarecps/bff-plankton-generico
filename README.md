# BFF Plankton Genérico

Este projeto é um Backend for Frontend (BFF) genérico construído com Spring Boot.

## Requisitos Funcionais

- **Endpoint Principal**: `/v1/planos`
- **Métodos Suportados**:
  - `GET /v1/planos`: Retorna uma lista de tarefas (Mock).
  - `GET /v1/planos/{id}`: Retorna uma tarefa específica por ID (Mock).
  - `POST /v1/planos`: Cria uma nova tarefa (Mock).
    - **Comportamento Especial**: Se a `descricao` for enviada como `"GERAR_ERRO"`, retorna HTTP 422 com uma lista de erros criativos (útil para testes de tratamento de erro no frontend).
  - `PUT /v1/planos/{id}`: Atualiza uma tarefa existente (Mock).
  - `PATCH /v1/planos/{id}`: Atualiza parcialmente uma tarefa (Mock).
  - `DELETE /v1/planos/{id}`: Remove uma tarefa (Mock).
- **Domínio de Retorno**: Objeto `Tarefa` contendo:
  - `id` (Long)
  - `descricao` (String)

- **Novo Serviço: TarefasService**
  - Método `retornarComErros()`:
    - Retorna `422 Unprocessable Entity`.
    - Retorna lista de erros criativos:
      1. "O capacitor de fluxo está sem plutônio."
      2. "O gnomo do servidor entrou em greve por falta de café."
      3. "A rebimboca da parafuseta recusou a conexão TCP/IP."

## Tecnologias

- Java 17
- Spring Boot 3.4.2
- Maven

## Configuração de Dependências (GitHub Packages)

Este projeto utiliza dependências hospedadas no **GitHub Packages** (especificamente `lib-fabao-commons-core`). Para que o Maven consiga baixá-las, é necessário configurar a autenticação no seu arquivo `~/.m2/settings.xml`.

1. Gere um **Personal Access Token (PAT)** no GitHub com o escopo `read:packages`.
2. Adicione (ou edite) o arquivo `~/.m2/settings.xml` com o seguinte conteúdo:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <servers>
    <server>
      <id>github</id>
      <username>SEU_USUARIO_GITHUB</username>
      <password>SEU_PERSONAL_ACCESS_TOKEN</password>
    </server>
  </servers>
</settings>
```

> **Nota:** O `<id>github</id>` no `settings.xml` deve corresponder ao id do repositório definido no `pom.xml`.

## Como Executar

Para executar o projeto com as configurações padrão:

```bash
./mvnw spring-boot:run
```

### Perfil Local

Foi configurado um perfil local para desenvolvimento que utiliza a porta **7080**. Para executar usando este perfil:

```bash
$ mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### VS Code

O projeto inclui uma configuração de lançamento (`launch.json`) para o VS Code. Para depurar:

1. Abra a aba "Run and Debug" (ou pressione `F5`).
2. Selecione a configuração **BffPlanktonGenerico (Local)**.
3. Clique no botão de Play.

Esta configuração já inicializa a aplicação com o perfil `local` ativo.

## Exemplos de Teste (cURL)

Abaixo estão exemplos de comandos `curl` para testar os endpoints da aplicação rodando localmente na porta **7080**.

### Listar todos os planos
```bash
curl -v http://localhost:7080/v1/planos
```

### Buscar plano por ID
```bash
curl -v http://localhost:7080/v1/planos/1
```

### Criar novo plano
```bash
curl -v -X POST http://localhost:7080/v1/planos \
  -H "Content-Type: application/json" \
  -d '{"descricao": "Nova Tarefa Importante"}'
```

### Atualizar plano (Completo)
```bash
curl -v -X PUT http://localhost:7080/v1/planos/1 \
  -H "Content-Type: application/json" \
  -d '{"descricao": "Tarefa Atualizada Completa"}'
```

### Atualizar plano (Parcial)
```bash
curl -v -X PATCH http://localhost:7080/v1/planos/1 \
  -H "Content-Type: application/json" \
  -d '{"descricao": "Apenas Descrição Atualizada"}'
```

### Deletar plano
```bash
curl -v -X DELETE http://localhost:7080/v1/planos/1
```

Ou, se estiver usando o jar diretamente:

```bash
java -jar target/bff-plankton-generico-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```
