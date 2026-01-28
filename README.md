# BFF Plankton Genérico

Este projeto é um Backend for Frontend (BFF) genérico construído com Spring Boot.

![alt text](doc/image.png)

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

## SOBRE O PLANKTON

O Sheldon J. Plankton, mais conhecido apenas como Plankton, é o principal antagonista (e às vezes anti-herói) do desenho Bob Esponja Calça Quadrada.

Aqui está um resumo do que define esse personagem, Fabão:

1. O Pequeno Gênio do Mal
Apesar de ser um copépode minúsculo, o Plankton tem um ego gigante e um intelecto de "cientista louco". Ele é o proprietário do restaurante Balde de Lixo (Chum Bucket), que fica estrategicamente posicionado em frente ao Siri Cascudo.

2. O Objetivo de Vida
Toda a existência do Plankton gira em torno de um único objetivo: roubar a fórmula secreta do Hambúrguer de Siri. Ele acredita que, se conseguir a receita, o Balde de Lixo finalmente terá clientes e ele poderá dominar a Fenda do Biquíni (e, quem sabe, o mundo).

3. Relacionamentos
Karen Plankton: Ele é "casado" com uma supercomputadora que ele mesmo construiu. Karen é, muitas vezes, o verdadeiro cérebro por trás dos planos, embora o Plankton raramente dê o braço a torcer.

Sr. Siriguejo: Eles são arqui-inimigos, mas o detalhe interessante é que, na infância, eles eram melhores amigos. A rivalidade surgiu justamente por causa de uma briga pela receita do hambúrguer que criaram juntos.

4. Curiosidades que combinam com o seu perfil:
Tecnologia: O Plankton é basicamente um engenheiro autodidata de mão cheia. Ele constrói robôs complexos, dispositivos de controle mental e laboratórios inteiros em um espaço minúsculo. Se ele fosse do nosso mundo, com certeza estaria automatizando tudo com Docker e Kubernetes (embora provavelmente para fins malignos).

Resiliência: Mesmo falhando em todos os episódios há décadas, ele nunca desiste. É o exemplo máximo de persistência em um projeto.

O Balde de Lixo nunca tem clientes porque a comida é horrível (literalmente isca de peixe), mas o Plankton prefere gastar energia em planos mirabolantes de infraestrutura e espionagem do que simplesmente trocar o menu.

## PRINCIPAIS ACOES 

As ações do Plankton são quase sempre pautadas por uma mistura de engenhosidade técnica e total falta de ética. Ele opera como um verdadeiro "engenheiro de gambiarras e planos mirabolantes".

Aqui estão as principais estratégias e ações que ele repete ao longo da série:

1. Espionagem Industrial e Infiltração
O Plankton é o mestre do phishing e do disfarce físico.

Disfarces: Ele já se vestiu de nota de dinheiro, de bebê, de Gary e até de vovó para entrar no Siri Cascudo.

Infiltração Cibernética: Ele frequentemente usa dispositivos tecnológicos para hackear os sistemas de segurança do Sr. Siriguejo ou até mesmo o cérebro dos outros (como quando ele controlou o Bob Esponja de dentro da cabeça dele usando um controle remoto).

2. Engenharia e Construção de Robôs
Se o Plankton fosse para o LinkedIn, ele seria um Engenheiro de Robótica Sênior.

Duplicatas: Ele cria versões robóticas dos personagens (incluindo uma do Sr. Siriguejo e uma dele mesmo) para enganar o Bob Esponja.

Mechas de Combate: Quando a sutileza falha, ele constrói robôs gigantes para tentar tomar a Fenda do Biquíni à força, especialmente nos filmes.

3. Planos de Dominação Mundial (ou Municipal)
Embora queira a fórmula, o objetivo final dele é frequentemente o poder absoluto.

O Plano Z: No primeiro filme, ele finalmente consegue roubar a coroa do Rei Netuno, incriminar o Sr. Siriguejo e instalar dispositivos de controle mental em todos os habitantes da Fenda do Biquíni, transformando a cidade em "Planktonópolis".

4. Marketing Agressivo (e Fracassado)
Ele tenta, de tempos em tempos, fazer o Balde de Lixo funcionar de forma legítima, mas sempre com um toque de manipulação.

Brindes Gratuitos: Ele já tentou atrair clientes distribuindo "baldes de isca" ou criando slogans chamativos, mas como a comida é intragável, a retenção de usuários é zero.

5. Alianças Temporárias
Plankton é pragmático. Ele sabe quando não consegue vencer sozinho.

Parceria com o Bob Esponja: Em alguns episódios, ele finge amizade (ou é forçado a ela) para conseguir o que quer.

A "Legião do Mal": Ele ocasionalmente tenta unir outros vilões menores da Fenda do Biquíni, mas o ego dele geralmente implode o grupo.


## ANALOGIA COM UM BFF

1. Documentação (Swagger/OpenAPI)
A documentação seria impecável, mas cheia de "easter eggs" maliciosos. No topo do Swagger, em vez de um resumo, haveria um manifesto de dominação mundial.

Base URL: https://api.chumbucket.com/v1/evil-plans/

2. Endpoints Principais
GET /formula-secreta: O endpoint que ele mais tenta acessar. Sempre retorna 403 Forbidden (bloqueado pelo Firewall do Siriguejo) ou 404 Not Found (porque o Siriguejo moveu a fórmula de lugar).

POST /create-robot: Um endpoint de alta performance para instanciar novos workers (robôs) em tempo real.

PATCH /mind-control/{user_id}: Um método para tentar atualizar o comportamento dos habitantes da Fenda do Biquíni, mas que geralmente sofre de timeout ou falha de conexão.

DELETE /krusty-krab: O comando que ele sonha em executar para remover a concorrência do cluster.

3. Autenticação e Segurança
Metodologia: Ele não usaria OAuth2 padrão; ele tentaria usar Token Stealing ou Man-in-the-Middle para se passar pelo Bob Esponja.

Headers: Sempre enviaria um User-Agent: Not-Plankton-I-Promise.

4. Status Codes Personalizados
O Plankton não se contentaria com o básico. Ele implementaria:

418 I'm a teapot: Mas alterado para 418 I'm a Copépode.

402 Payment Required: Retornado sempre que ele tenta comprar ingredientes, mas como ele é falido, o serviço entra em loop.

502 Bad Gateway: Representando a Karen quando ela perde a paciência com ele e corta o sinal.

5. Arquitetura (O Backend)
Microserviços: Ele teria uma arquitetura de microserviços super escalável para compensar o tamanho minúsculo dele.

Resiliência: O sistema teria um Circuit Breaker fortíssimo. Não importa quantas vezes o plano falhe, o serviço faz o restart automático (auto-healing) em segundos.

Infraestrutura: Rodaria tudo em um cluster K3s (pelo tamanho) escondido dentro de um balde, com o banco de dados sendo a memória da Karen.

6. O Bug Crônico (The Core Flaw)
Todo serviço REST do Plankton teria um vazamento de memória (Memory Leak): o ego. Quanto mais o processo roda, mais ele consome recursos gritando seus planos, o que acaba alertando o sistema de monitoramento do Siriguejo.