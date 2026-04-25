# Decisões Técnicas

- Frontend em AngularJS 1.x com `angular-route` e `controllerAs vm`.
- Backend em Spring Boot com separação entre `entity`, `repository`, `business` e `service`.
- Banco PostgreSQL com `ddl.sql` e `dml.sql` manuais, executados automaticamente via Docker Compose.
- `docker-compose.yml` integrado com `database`, `backend` e `frontend`.
- A listagem de leilões recebe o total calculado no backend para simplificar o front.
- O frontend foi implementado em AngularJS porque esta é a stack atual citada na entrevista com a AZ.
