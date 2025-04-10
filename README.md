# Hubspot Integration API
Esse repositório implementa a Hubspot Integration API: API desenvolvida para integrar a API do Hubspot possibilitando autenticação via OAuth2, criação de contatos e disponibilização de um Webhook para escuta de eventos notificação pela API do Hubspot

# Tecnologias
- Java 17+
- Spring 3.4.4+

# Funcionalidade idealizadas
Dentre as funcionalidade idealizadas estavam 
- Geração da Authorization URL (Implementada)
- Processamento do Callback OAuth (Implementada)
- Criação de Contatos (Implementada)
- Recebimento de Webhook para Criação de Contatos (Não testado)


# Bibliotecas 
- Spring Cloud OpenFeign: Utilizado para a comunicação com a API do HubSpot. Com ele, foi possível definir interfaces Java para representar chamadas HTTP externas, mantendo o código limpo e com baixo acoplamento.
- Caffeine + Spring Cache: Empregado para armazenar em cache algumas respostas da API do HubSpot, evitando chamadas repetidas e melhorando o desempenho geral da aplicação. A integração com o Spring Cache tornou a configuração e utilização bem simples e eficiente.
- Spring Retry: Implementado para adicionar lógica de reexecução automática em chamadas HTTP que possam falhar por instabilidades momentâneas, como timeouts da API externa.
- Spring AOP: Utilizado em conjunto com o Spring Retry para aplicar a lógica de reexecução de forma transparente, sem a necessidade de poluir o código principal com estruturas de controle adicionais.

# Melhorias Futuras
- Persistência dos eventos recebidos em um banco de dados.
- Adição de autenticação/autorização na API.
- Adição de autenticação/verificação do webhook.
- Melhorar o tratamento de erros e exceções
- Melhorar o fluxo de atualização do token de acesso
- Organização de ambiente com Docker Compose

# Como executar

## Requisitos

- Java 17+
- Spring 3.4.4+

## Clone o repositório 

```bash
git clone https://github.com/italoou/hubspot-oauth-integration.git
cd hubspot-oauth-integration
```

## Execução

```bash
mvn clean install
java -jar ./target/hubspot-integration-api-0.0.1-SNAPSHOT.jar
```

## Acesso

Após a execução dos comandos a API estará disponivel na porta padrão, qualquer necessidade de modificação deverá ser replicada no código.

- API: http://localhost:8080

# Autoria

Esse projeto foi desenvolvido por Ítalo Lima
- Github: https://github.com/italoou