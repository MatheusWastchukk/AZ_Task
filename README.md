# Teste Tecnico AZ

Projeto implementado em `AngularJS + Spring Boot + PostgreSQL`.

Escolhi AngularJS pois e a stack atual dos projeto, como citado na entrevista.

## Estrutura

- `database/ddl.sql`
- `database/dml.sql`
- `docs/DECISOES_TECNICAS.md`
- `leilao-api/`
- `leilao-app/`
- `docker-compose.yml`

## Como subir

```bash
docker compose up --build
```

## Portas

- Frontend: `http://localhost:8080`
- Backend: `http://localhost:8081`
- PostgreSQL: `localhost:5432`

## Escopo atual

- DDL e DML iniciais com as tabelas `empresa`, `unidade`, `leilao`, `lote` e `comprador`
- CRUD REST para todas as tabelas
- Tela principal com menu
- Tela de unidades com CRUD
- Tela de empresas com consulta, inclusao, edicao e exclusao
- Tela de leiloes com total calculado no backend

## Observacao

O repositorio publico original usa Vue no frontend. Nesta entrega, os requisitos funcionais foram mantidos e a camada cliente foi adaptada para AngularJS por alinhamento ao contexto real informado pela AZ.
