# Dúvidas Iniciais Antes da Implementação

Este documento reúne as dúvidas que surgiram após a leitura de:

- [GUIA_TESTE_AZ_ANGULARJS_SPRING.md](/c:/Users/Matheus/Documents/Projects/AZ_test/AZ_Task/GUIA_TESTE_AZ_ANGULARJS_SPRING.md)
- [README.md](/c:/Users/Matheus/Documents/Projects/AZ_test/Selecao-Dev-AZ/README.md)
- [variables.env](/c:/Users/Matheus/Documents/Projects/AZ_test/Selecao-Dev-AZ/variables.env)
- [docker-database.yml](/c:/Users/Matheus/Documents/Projects/AZ_test/Selecao-Dev-AZ/docker-database.yml)
- diagrama do banco em `Selecao-Dev-AZ/leilao-api/src/main/resources/sql/database.png`

O objetivo é alinhar decisões antes de começarmos a implementação real.

## 1. Estrutura da Entrega

1. O projeto final deve ser criado inteiro dentro de `AZ_Task/` mesmo, ou você prefere criar uma nova subpasta raiz lá dentro, por exemplo `AZ_Task/az-teste-matheus/`?

R: Crie em AZ_Task

2. Você quer manter a estrutura proposta no guia:
   `database/`, `docs/`, `leilao-api/`, `leilao-app/`, `docker-compose.yml` e `README.md`?

R: Pode ser

3. Podemos considerar o `Selecao-Dev-AZ` apenas como referência e não reaproveitar código diretamente dele, exceto arquivos de configuração e entendimento do desafio?

R: Isso, reaproveite apenas configurações e arquivos necessários pro desafio

## 2. Docker Compose Completo

1. Quando você disse para subir a aplicação inteira via `docker-compose`, a sua expectativa é termos estes serviços?
   `postgres`, `backend` e `frontend`
2. Você quer que o `frontend` rode em container de desenvolvimento ou prefere build estático servido por `nginx`?

R: Faça da maneira que considerar a melhor pensando no projeto

3. Você quer que o `backend` faça o build dentro do Docker, ou prefere uma imagem multi-stage já pronta para execução?

R:  Faça da maneira que considerar a melhor pensando no projeto

4. Deseja que os scripts `ddl.sql` e `dml.sql` sejam executados automaticamente na subida do banco?

R: Sim

5. Você quer priorizar portabilidade total, mesmo que o tempo de build fique maior?

R: A ideia é que eu não precise instalar postgres, java 8 e AngularJs no meu pc, para que tudo rode por meio de docker

## 3. Banco de Dados

1. Posso assumir como modelo oficial o diagrama encontrado em `database.png`, com as tabelas:
   `empresa`, `leilao`, `lote`, `unidade` e `comprador`?

   R: Sim

2. Posso assumir que o nome correto das tabelas na entrega será exatamente esse, sem prefixos como `tb_`?

R: Sim, não usará tb_

3. Para o exercício 3, você quer mesmo CRUD REST também para a tabela associativa `comprador`?

R: Siga as instruções do desafio, da maneira mais fiel possível e anote tudo que não for possível fazer em um arquivo separado  

4. No relacionamento `leilao.vendedor -> empresa.id`, podemos assumir que o vendedor é sempre uma empresa?

R: Siga o que o desafio manda, se ele não citar nada sobre, siga a interpretação que julgar certa

5. Para o cálculo do total do leilão, posso assumir a fórmula:
   `SUM(lote.quantidade * lote.valorInicial)` por leilão?

   R: Siga o que o desafio manda, se ele não citar nada sobre, siga a interpretação que julgar certa

## 4. Regras Funcionais e Interpretações

1. A inconsistência do README sobre `#/leilao/:id` para edição de empresa será tratada oficialmente como erro do enunciado, usando:
   `#/empresa` e `#/empresa/:id`?

   R: Sim, o ideal é que use empresa pra empresa e leilao pra leilao

2. Na tela de empresas, além da listagem somente leitura, devemos incluir ações de `novo`, `editar` e `excluir`, certo?

R: Siga o que o desafio manda, se ele não citar nada sobre, siga a interpretação que julgar certa

3. Na tela de unidades, você prefere CRUD em tabela com formulário inline na mesma página, ou aceita formulário simples abaixo/ao lado da grid?

R: Se o desafio não cita nada de design, utilize a abordagem de modal, com editar e criar abrindo um modal com form

4. Na tela de leilões, basta a listagem somente leitura, sem tela de cadastro própria no front?

R: Se não é pedido no desafio, o ideal seria não fazer, mas vamos deixar o crud completo, como os outros 

5. Para as demais tabelas exigidas no CRUD REST, podemos manter apenas endpoints no back-end sem tela dedicada no front?

R: Esses sim, se não foi solicitado no desafio, deixaremos apenas como endpoints


## 5. Stack e Ferramentas

1. Podemos fixar `AngularJS 1.x` com `angular-route` e uma estrutura simples baseada em `controllerAs vm`?

R: Sim

2. Você quer usar `Bootstrap` para acelerar a interface?

R: Podemos utilizar

3. No back-end, podemos seguir com `Spring Boot` + `Spring Data JPA` + `Validation`?

R: Sim 

4. Sobre Java, você quer que eu siga `Java 8`, como sugere o guia, ou existe outra versão que você prefira?

R: Utilize Java 8

5. Você quer usar `Maven` ou `Gradle` no back-end?

R: utilize o que for mais correto para essa stack

## 6. Execução Local e Portas

1. Podemos padronizar, por exemplo:
   `frontend: 8080`, `backend: 8081`, `postgres: 5432`?

   R: Sim

2. A porta `8081` do back-end, vista no projeto antigo, deve ser mantida?

R: Siga tudo que for citado no desafio, o restante deve ser feito seguindo as melhores práticas de desenvolvimento

3. Você quer que o front consuma a API via proxy/reverse proxy no Docker, ou pode chamar a API diretamente por URL do back-end?

R: Siga o que for decidido no desafio, o restante deve ser feito seguindo as melhores práticas de desenvolvimento

## 7. Nível de Entrega

1. Você quer uma entrega mais enxuta e segura, focada em cumprir 100% do teste com boa organização?

R: Isso, o inicial é cumprir 100% do teste da melhor maneira possível, depois focamos em melhorar

2. Ou quer também um refino extra de apresentação visual, autenticação mockada e documentação mais robusta?

R: Foco no teste

3. Devemos priorizar primeiro uma versão funcional end-to-end em Docker e depois refinamentos?

R: Foque em entregar tudo solicidado no teste, depois iremos refinar

## 8. README e Posicionamento

1. No README final, você quer uma justificativa explícita dizendo que a troca de Vue para AngularJS foi decisão deliberada com base no que ouviu na entrevista?

R: Apenas coloque uma observação do porque escolher angularjs pro frontend: "Escolhi pois é a stack atual dos projeto, como citado na entrevista"

2. Você quer citar o nome da empresa AZ diretamente no README da entrega final, ou prefere um texto mais neutro?

R: Pode citar

## 9. Proposta de Assumir Como Padrão Se Você Concordar

Se você quiser acelerar, posso seguir com estas decisões por padrão:

- tudo dentro de `AZ_Task/`
- estrutura nova e limpa, sem reaproveitar código do Vue
- `docker-compose.yml` com `postgres`, `backend` e `frontend`
- execução automática de `ddl.sql` e `dml.sql`
- AngularJS 1.x + `angular-route` + Bootstrap
- Spring Boot com Maven e Java 8
- front com telas de `unidades`, `empresas`, `empresa-form` e `leiloes`
- CRUD REST de todas as tabelas do diagrama
- foco inicial em funcionalidade completa e portável, depois refino visual
