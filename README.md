# Teste Técnico AZ

Projeto implementado em `AngularJS + Spring Boot + PostgreSQL`.

Escolhi AngularJS pois é a stack atual dos projetos, como citado na entrevista.

## Objetivo

Entrega do teste técnico da AZ com:

- scripts SQL manuais para criação e carga inicial do banco
- API REST completa para todas as tabelas do modelo
- frontend AngularJS com as telas pedidas no desafio
- execução completa via Docker

## Stack

- Frontend: `AngularJS 1.8` + `angular-route` + `Bootstrap 5` + `nginx`
- Backend: `Java 8` + `Spring Boot` + `Spring Data JPA` + `Validation`
- Banco: `PostgreSQL 10`
- Infra: `Docker Compose`

## Justificativa do Frontend

O repositório público do teste apresenta o client em Vue. Nesta entrega, os requisitos funcionais foram mantidos, mas o frontend foi implementado em AngularJS por alinhamento ao contexto real informado pelo time da AZ durante a entrevista.

Além disso, mantive uma página inicial em `#/` por decisão de experiência de navegação. Na minha concepção de sistema, o ideal é abrir primeiro em uma tela home e, a partir dela, seguir para as telas de registro e consulta, em vez de iniciar diretamente em uma tela de cadastro. As rotas exigidas pelo desafio continuam disponíveis normalmente.

## Estrutura

- `database/ddl.sql`
- `database/dml.sql`
- `docs/DECISOES_TECNICAS.md`
- `leilao-api/`
- `leilao-app/`
- `docker-compose.yml`
- `variables.env`

## Como Executar

Na raiz `AZ_Task`:

```bash
docker compose up --build
```

## Portas

- Frontend: `http://localhost:8080`
- Backend: `http://localhost:8081`
- PostgreSQL: `localhost:5432`

## Rotas do Frontend

- `#/`
- `#/unidades`
- `#/empresas`
- `#/empresa`
- `#/empresa/:id`
- `#/leiloes`

## Endpoints Principais

- `GET|POST /unidades`
- `GET|PUT|DELETE /unidades/{id}`
- `GET|POST /empresas`
- `GET|PUT|DELETE /empresas/{id}`
- `GET|POST /leiloes`
- `GET|PUT|DELETE /leiloes/{id}`
- `GET|POST /lotes`
- `GET|PUT|DELETE /lotes/{id}`
- `GET|POST /compradores`
- `GET|PUT|DELETE /compradores/{empresaId}/{leilaoId}`

## O que Foi Entregue

- DDL com sequences, PK, FK, `UNIQUE` e `NOT NULL` conforme o modelo adotado do desafio
- DML com pelo menos 10 registros em cada tabela
- CRUD REST para `empresa`, `unidade`, `leilao`, `lote` e `comprador`
- Tela principal com menu, página inicial em `#/` e navegação entre views
- Tela de unidades com CRUD completo
- Tela de empresas com consulta, inclusão, edição e exclusão
- Tela de leilões com vendedor, início previsto e total calculado no backend
- Validações na tela de empresa para obrigatoriedade, e-mail, URL, tamanho máximo, máscara de CNPJ, telefone, CEP e número
- Frontend empacotado como site estático e servido por `nginx`

## Validações Executadas

- subida completa da stack com `docker compose up --build`
- validação de resposta do frontend em `http://localhost:8080`
- validação de resposta da API em `http://localhost:8081`
- conferência de carga inicial com pelo menos 10 registros em `unidade`, `empresa`, `leilao`, `lote` e `comprador`
- validação de CRUD para `unidades`
- validação de CRUD para `empresas`
- validação de CRUD para `leiloes`
- validação de CRUD para `lotes`
- validação de CRUD para `compradores`
- validação de regras da tela de empresa, incluindo obrigatoriedade, formatos e limites
- validação do cálculo do total do leilão no backend

## Validações da Tela de Empresa

- `razaoSocial`, `cnpj` e `usuario` obrigatórios
- `cnpj` no formato `00.000.000/0000-00`
- `telefone` no formato `(00) 0000-0000` ou `(00) 00000-0000`
- `cep` no formato `00000-000`
- `numero` aceitando apenas dígitos
- `email` validado pelo navegador e backend
- `site` validado como URL
- limites máximos de tamanho no front e no backend

## Observações Técnicas

- A camada `business` no backend foi mantida porque o enunciado do teste pede explicitamente essa separação entre `service`, `business`, `repository` e `entity`.
- O total do leilão é calculado no backend como soma de `quantidade * valorInicial` dos lotes vinculados.
- O banco é inicializado automaticamente com `ddl.sql` e `dml.sql` ao subir o container do PostgreSQL pela primeira vez.
- A API foi padronizada majoritariamente em plural, como `GET /empresas`, `GET /unidades` e `GET /leiloes`. A rota `#/empresa` no frontend foi mantida apenas para o formulário de inclusão e `#/empresa/:id` para edição.
- O recurso `comprador` utiliza chave composta formada por `empresa` e `leilao`. Por isso, as operações específicas do CRUD usam o formato `GET|PUT|DELETE /compradores/{empresaId}/{leilaoId}` em vez de um identificador simples.

## Melhorias Futuras

- testes automatizados de backend e frontend
- refinamento visual da interface
- padronização adicional de mensagens de erro no frontend
