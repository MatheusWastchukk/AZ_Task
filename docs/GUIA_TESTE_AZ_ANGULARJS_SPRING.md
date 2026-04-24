# Guia completo — Teste AZ em AngularJS + Spring Boot

## Objetivo deste documento
Este arquivo é o **guia oficial de execução** do teste técnico da AZ, considerando dois fatos ao mesmo tempo:

1. o **README público do repositório** descreve o desafio com **Vue + Spring Boot + PostgreSQL**;
2. durante a entrevista, o **time da AZ informou de forma confiável** que o contexto real deles envolve **AngularJS no front-end** e **Java/Spring no back-end**.

A conclusão estratégica, portanto, é:

- o **domínio funcional e os exercícios do README continuam valendo**;
- o **front-end deve ser implementado em AngularJS**, para demonstrar aderência ao ambiente real da empresa;
- o **back-end deve ser implementado em Java com Spring Boot**;
- o **banco deve ser PostgreSQL**;
- a entrega precisa deixar essa decisão **bem documentada**, para que o avaliador entenda que ela foi deliberada e alinhada ao contexto da AZ.

Este guia foi escrito para ser útil de duas formas:

- servir como **passo a passo extremamente detalhado** para você executar o teste;
- servir como **documento-base para outras IAs**, permitindo continuidade sem perda de contexto.

---

# 1. Decisão oficial de stack

## 1.1 Stack a ser usada
A implementação final deve usar:

- **Front-end:** AngularJS (1.x)
- **Back-end:** Java + Spring Boot
- **Banco de dados:** PostgreSQL

## 1.2 Justificativa da decisão
Mesmo que o README público use Vue, a decisão correta para esta entrega é usar **AngularJS**, porque:

- a informação veio diretamente do time da AZ durante a entrevista;
- o teste também mede **aderência ao ambiente real da empresa**;
- entregar em AngularJS mostra que o candidato consegue atuar em **sistemas legados**, além de entender o contexto do time;
- a escolha não foi por modernidade da stack, mas por **alinhamento estratégico com a realidade da empresa**.

## 1.3 Frase oficial para README/apresentação
Use algo próximo disto no README final e na apresentação:

> O repositório público do teste apresenta a camada cliente em Vue, porém optei por implementar o front-end em AngularJS por alinhamento ao contexto real da AZ, conforme informado pelo time durante a entrevista. Mantive os requisitos funcionais do enunciado e adaptei a implementação para a stack utilizada pela empresa.

---

# 2. Como começar: clonar ou criar novo projeto?

## 2.1 Recomendação principal
A recomendação é:

- **clonar o repositório original apenas como referência**;
- **criar uma solução nova e limpa para entrega**.

## 2.2 O que fazer na prática
### Passo 1 — clonar o repositório original
Clone o projeto original somente para consultar:

- enunciado;
- diagrama/modelo do banco;
- nomes das telas;
- regras pedidas;
- estrutura geral de domínio;
- arquivos auxiliares como `docker-database.yml` e `variables.env`.

### Passo 2 — não desenvolver em cima do front Vue
Não vale a pena tentar converter o `leilao-app` de Vue para AngularJS dentro da mesma base, porque isso tende a:

- misturar convenções incompatíveis;
- gerar ruído no histórico do projeto;
- dificultar a organização da entrega;
- passar impressão de adaptação improvisada.

### Passo 3 — criar um projeto novo seu
Crie uma nova raiz para a solução final, por exemplo:

```text
AZ_Task/
  database/
    ddl.sql
    dml.sql
  docs/
    DECISOES_TECNICAS.md
    APRESENTACAO.md
  leilao-api/
  leilao-app/
  docker-compose.yml
  README.md
```

## 2.3 Conclusão objetiva
**Sim, clone o repositório original.**
Mas **apenas para referência**.
A entrega deve ser um **projeto novo**, seu, implementado em **AngularJS + Spring Boot**.

---

# 3. O que exatamente o teste pede

## 3.1 Requisitos principais do enunciado
O teste exige, em essência:

1. **DDL** completo do banco, com tabelas, colunas, PK, FK, UNIQUE, sequences e NOT NULL;
2. **DML** com massa inicial, com pelo menos 10 registros por tabela;
3. **CRUD REST** das tabelas;
4. **Tela principal** com menu e navegação;
5. **Tela de Unidades** com CRUD completo em grid editável;
6. **Tela de Empresas** com consulta em grid somente leitura;
7. **Tela de Leilões** com consulta em grid somente leitura e cálculo do total do leilão;
8. **Tela de cadastro/edição de Empresas** com validações de campos obrigatórios, formatos, máscaras e limites.

## 3.2 O que deve ser mantido mesmo adaptando para AngularJS
Mesmo mudando Vue para AngularJS, você deve preservar:

- nomes conceituais das páginas;
- comportamento funcional solicitado;
- endpoints REST esperados;
- separação de camadas no back-end;
- regras de listagem e edição.

## 3.3 Inconsistência do enunciado
O README aparentemente contém um erro ao mencionar a rota de edição de empresa como `#/leilao/:id` na parte da tela de empresa. Para a sua implementação, o correto é tratar isso como um erro textual e usar:

- `#/empresa` para inclusão;
- `#/empresa/:id` para edição.

No README final, você pode mencionar isso de forma curta em uma seção de observações.

---

# 4. Estratégia geral de execução

## 4.1 Ordem ideal de trabalho
A ordem mais segura é:

1. entender o modelo do banco;
2. criar `ddl.sql`;
3. criar `dml.sql`;
4. subir o PostgreSQL;
5. implementar o back-end inteiro primeiro;
6. testar todos os endpoints com Postman/Insomnia;
7. implementar o front-end AngularJS por telas;
8. revisar validações e fluxos;
9. escrever README final e material da apresentação.

## 4.2 Por que fazer o back primeiro
Porque o front depende de:

- contratos de API;
- formato de payload;
- regras de validação;
- estrutura dos dados.

Com o back pronto, o AngularJS vira uma camada de consumo mais previsível.

---

# 5. Estrutura recomendada do projeto

## 5.1 Estrutura da raiz
```text
az-teste-matheus/
  database/
    ddl.sql
    dml.sql
  docs/
    DECISOES_TECNICAS.md
    APRESENTACAO.md
  leilao-api/
  leilao-app/
  docker-compose.yml
  README.md
```

## 5.2 Estrutura recomendada do back-end
Seguindo o padrão pedido no enunciado:

```text
leilao-api/
  src/main/java/br/com/az/leilao/
    entity/
    repository/
    business/
    service/
    dto/
    config/
    exception/
  src/main/resources/
    application.properties
```

### Interpretação das camadas
- `entity`: entidades JPA
- `repository`: interfaces de acesso a dados
- `business`: regras de negócio e orquestração
- `service`: controllers REST
- `dto`: objetos de entrada/saída quando necessário
- `config`: CORS, Swagger (se usar), configs gerais
- `exception`: tratamento padronizado de erro

## 5.3 Estrutura recomendada do front-end AngularJS
```text
leilao-app/
  app/
    app.module.js
    app.routes.js
    core/
      api/
        api.config.js
      services/
        unidade.service.js
        empresa.service.js
        leilao.service.js
      constants/
      filters/
      directives/
    components/
      menu/
        menu.component.js
        menu.component.html
    views/
      unidades/
        unidades.controller.js
        unidades.service.js (opcional, se quiser separado por view)
        unidades.html
      empresas/
        empresas.controller.js
        empresas.html
      empresa-form/
        empresa-form.controller.js
        empresa-form.html
      leiloes/
        leiloes.controller.js
        leiloes.html
    shared/
      utils/
      validators/
      masks/
  assets/
    css/
    js/
  index.html
  package.json
```

## 5.4 Observação importante sobre AngularJS
Como AngularJS não usa a mesma organização do Angular moderno, a recomendação é manter clareza usando:

- módulos bem definidos;
- services separados por domínio;
- controllers enxutos;
- templates HTML por tela;
- pouca lógica no HTML;
- funções bem nomeadas no `$scope` ou preferencialmente `controllerAs`.

### Recomendação adicional
Se possível, use **`controllerAs vm`** em vez de abusar de `$scope`, porque isso deixa a base mais organizada e passa melhor impressão técnica.

---

# 6. Ambiente de desenvolvimento

## 6.1 Ferramentas recomendadas
- **Java:** 8
- **Node.js:** versão compatível com o setup do AngularJS escolhido
- **npm**
- **PostgreSQL** ou Docker
- **IDE back-end:** IntelliJ / VS Code / STS
- **IDE front-end:** VS Code
- **API client:** Postman ou Insomnia
- **Git**

## 6.2 Banco via Docker
Vale muito a pena usar Docker para o banco, pois facilita:

- setup rápido;
- repetibilidade;
- limpeza do ambiente;
- demonstração de organização.

Você pode aproveitar a ideia do arquivo `docker-database.yml` do repositório original, mas o ideal é consolidar na sua entrega um `docker-compose.yml` claro e simples.

### Exemplo de composição esperada
- container PostgreSQL
- volume persistente
- variáveis de ambiente para banco, usuário e senha
- porta exposta, por exemplo `5432`

---

# 7. Passo a passo detalhado de execução

# Etapa A — Preparação do projeto

## A.1 Criar a pasta da solução final
Criar uma pasta raiz nova, por exemplo:

```text
az-teste-matheus
```

## A.2 Inicializar Git
Inicializar um repositório novo.

## A.3 Criar a estrutura base de diretórios
Criar:

- `database/`
- `docs/`
- `leilao-api/`
- `leilao-app/`
- `README.md`

## A.4 Clonar o projeto original separadamente
Clone o projeto original fora da pasta final, algo como:

```text
referencias/Selecao-Dev-AZ/
```

Isso evita misturar as coisas.

---

# Etapa B — Banco de dados

## B.1 Entender o modelo relacional
Antes de escrever SQL, identificar todas as tabelas e relações a partir do diagrama do teste.

Você precisa mapear:

- entidades principais;
- colunas obrigatórias (`NOT NULL`);
- colunas únicas (`UNIQUE`);
- relacionamentos (`FK`);
- sequences;
- tipos adequados (`varchar`, `date`, `timestamp`, `numeric`, `bigint`, etc.).

## B.2 Criar o `ddl.sql`
O arquivo `database/ddl.sql` deve conter:

- criação de sequences;
- criação das tabelas;
- primary keys;
- foreign keys;
- unique constraints;
- not null;
- índices, se fizer sentido;
- ordem correta para evitar erro de dependência.

## B.3 Criar o `dml.sql`
O arquivo `database/dml.sql` deve conter massa inicial com **pelo menos 10 registros em cada tabela**.

### Boas práticas para o DML
- usar dados coerentes entre si;
- respeitar FKs;
- manter diversidade de registros;
- usar nomes realistas;
- garantir que existam dados suficientes para testar as telas;
- incluir leilões e lotes de forma que o cálculo do total seja visível.

## B.4 Validar scripts
Executar os scripts no PostgreSQL e garantir:

- nenhuma falha de sintaxe;
- nenhuma quebra de FK;
- sequences funcionando;
- inserts coerentes.

---

# Etapa C — Back-end Spring Boot

## C.1 Criar o projeto Spring Boot
Criar um projeto com dependências como:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Validation
- Lombok (opcional)

## C.2 Configurar `application.properties`
Definir:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.jpa.hibernate.ddl-auto=none`
- `spring.jpa.show-sql=true` (opcional durante desenvolvimento)
- configuração de dialect do PostgreSQL

### Observação importante
Como o teste pede **DDL/DML próprios**, não deixe o Hibernate gerar schema automaticamente.

## C.3 Criar entidades JPA
Criar uma entidade para cada tabela do modelo.

### Cuidados
- mapear corretamente `@Id`
- usar `@SequenceGenerator` quando necessário
- mapear relacionamentos com `@ManyToOne`, `@OneToMany`, etc.
- evitar recursão em JSON
- usar DTOs se necessário para resposta mais controlada

## C.4 Criar repositories
Um repository por entidade.

## C.5 Criar camada `business`
A camada `business` deve concentrar:

- regras de CRUD;
- validações adicionais de negócio;
- busca por ID com tratamento de não encontrado;
- atualização segura;
- exclusão com regra clara;
- transformação para DTO quando necessário.

## C.6 Criar camada `service`
Essa camada será a dos controllers REST.

### Padrão esperado
Para cada recurso:

- `GET /recurso`
- `GET /recurso/{id}`
- `POST /recurso`
- `PUT /recurso/{id}`
- `DELETE /recurso/{id}`

## C.7 Implementar CRUD de todas as tabelas
Mesmo que nem todas apareçam com interface completa no front, o enunciado pede CRUD REST das tabelas. Então o back deve cobrir todas.

## C.8 Endpoints principais que certamente precisam existir
Pelo menos:

- `/unidades`
- `/empresas`
- `/leiloes`
- e demais recursos que existirem no modelo, como lotes, vendedores, compradores etc., conforme o diagrama real.

## C.9 Tela de leilões: regra especial
A listagem de leilões precisa exibir:

- `razaoSocial` do vendedor
- `inicioPrevisto`
- `total do leilão`

Esse total deve ser a soma dos totais individuais de cada lote:

```text
quantidade * valorInicial
```

### Melhor abordagem
Criar um endpoint que já devolva um DTO pronto para a tela, por exemplo:

- `GET /leiloes`

retornando algo como:

```json
[
  {
    "id": 1,
    "razaoSocialVendedor": "Empresa X",
    "inicioPrevisto": "2026-04-25",
    "total": 150000.00
  }
]
```

Isso simplifica o front e mostra boa modelagem.

## C.10 Tratamento de erro
Implementar um handler global com respostas consistentes para:

- entidade não encontrada
- validação inválida
- conflito de unicidade
- erro interno

Mesmo que simples, isso valoriza bastante a solução.

## C.11 Testar tudo antes do front
Testar todos os endpoints no Postman/Insomnia.

### Checklist mínimo do back
- listar
- buscar por id
- inserir
- editar
- excluir
- erro ao buscar id inexistente
- erro de validação
- erro de campo único duplicado

---

# Etapa D — Front-end AngularJS

# D.1 Escolha de abordagem para AngularJS
Você pode montar o front AngularJS de duas formas:

## Opção 1 — AngularJS clássico simples
- `angular`
- `angular-route`
- templates HTML
- controllers
- services com `$http`

## Opção 2 — AngularJS com mais organização
- `angular`
- `angular-route`
- components onde fizer sentido
- `controllerAs vm`
- services dedicados
- estrutura modular mais limpa

### Recomendação
Use a **Opção 2**, pois ela passa imagem melhor.

## D.2 Dependências úteis
No mínimo:

- `angular`
- `angular-route`

Opcionalmente:

- biblioteca de máscara para AngularJS
- biblioteca de tabela, se quiser
- Bootstrap para layout rápido

### Recomendação prática
Para velocidade e visual aceitável:

- AngularJS
- angular-route
- Bootstrap
- tabela HTML simples estilizada

Isso tende a ser mais seguro do que depender de uma grid pesada se o prazo estiver apertado.

## D.3 Estrutura de rotas
Configurar rotas para:

- `#/unidades`
- `#/empresas`
- `#/empresa`
- `#/empresa/:id`
- `#/leiloes`

A raiz pode redirecionar para `#/unidades` ou ter uma home simples.

## D.4 App principal
Criar:

- `index.html`
- `app.module.js`
- `app.routes.js`

A tela principal deve conter:

- menu de navegação
- área principal de renderização (`ng-view`)

## D.5 Tela de Unidades
### Requisito
CRUD completo em uma grid editável.

### O que entregar
- listagem das unidades
- botão de novo
- edição de uma unidade
- exclusão
- atualização visual após operação

### Implementação recomendada
Em AngularJS, a forma mais simples e segura é:

- tabela com linhas
- botão “editar” em cada linha
- formulário simples na própria página ou em modal
- botão “salvar”
- botão “cancelar”
- botão “excluir” com confirmação

### Observação importante
O enunciado cita `v-data-table`, que é conceito do ecossistema Vue/Vuetify. Como a solução será em AngularJS, isso deve ser interpretado funcionalmente, não literalmente.

Ou seja: entregue uma **tabela funcional equivalente**, mesmo sem Vuetify.

## D.6 Tela de Empresas
### Requisito
Consulta parametrizada, somente leitura, exibindo:

- `cnpj`
- `razaoSocial`
- `telefone`
- `email`

### Além disso
Como o exercício 8 pede editar e excluir empresas a partir da consulta, essa tela também deve ter ações:

- editar
- excluir
- novo cadastro

Mesmo que a grid seja “somente leitura” em relação aos campos, ela pode ter botões de ação.

## D.7 Tela de cadastro/edição de Empresa
### Requisito principal
Tela mais completa do teste.

Precisa validar:

- obrigatórios
- máscaras
- número
- email
- url
- tamanho máximo

### O que essa tela deve ter
- modo inclusão
- modo edição
- carregamento de empresa por ID
- botão salvar
- botão cancelar/voltar
- mensagens de erro por campo
- feedback visual de validação

### Validações mínimas esperadas
- CNPJ obrigatório e formatado
- Razão social obrigatória
- Telefone em formato válido
- Email com regex adequada
- URL, se existir campo site/homepage
- limites de tamanho para textos
- campos numéricos aceitando apenas números quando aplicável

### Recomendação de UX
- aplicar máscara enquanto digita, quando viável
- mostrar mensagem abaixo do campo
- impedir submit inválido
- destacar campo inválido

## D.8 Tela de Leilões
### Requisito
Consulta somente leitura com:

- razão social do vendedor
- início previsto
- total do leilão

### O que fazer
- consumir endpoint já consolidado do back
- exibir tabela limpa
- formatar data
- formatar moeda

## D.9 Services do AngularJS
Criar services com `$http` para:

- `UnidadeService`
- `EmpresaService`
- `LeilaoService`

### Exemplo de responsabilidade
- `list()`
- `getById(id)`
- `create(payload)`
- `update(id, payload)`
- `remove(id)`

## D.10 Boas práticas no front
- não duplicar lógica de API em vários controllers
- centralizar URL base da API
- usar `controllerAs vm`
- tratar loading e erro básico
- evitar excesso de lógica direto no HTML

---

# 8. Plano de implementação por ordem real

## Dia/Bloco 1 — Base do projeto
1. criar pasta final
2. criar `docker-compose.yml`
3. subir PostgreSQL
4. montar `ddl.sql`
5. montar `dml.sql`
6. validar banco

## Dia/Bloco 2 — Back-end
1. criar Spring Boot
2. configurar conexão
3. criar entidades
4. criar repositories
5. criar business
6. criar controllers REST
7. testar endpoints

## Dia/Bloco 3 — Front-end base
1. criar app AngularJS
2. configurar rotas
3. criar menu
4. criar estrutura de services
5. configurar consumo da API

## Dia/Bloco 4 — Telas
1. unidades CRUD
2. empresas listagem
3. empresa cadastro/edição
4. leilões listagem

## Dia/Bloco 5 — Refino
1. validações
2. máscaras
3. tratamento de erro
4. revisão visual
5. README final
6. ensaio da apresentação

---

# 9. Decisões técnicas recomendadas

## 9.1 Banco
- scripts manuais em SQL
- sequences explícitas
- integridade referencial garantida

## 9.2 Back-end
- JPA apenas para acesso a dados
- schema gerenciado por SQL do teste
- DTO específico para listagem de leilões
- exceções tratadas centralmente

## 9.3 Front-end
- AngularJS por aderência ao stack real da AZ
- `angular-route` para páginas
- controllers pequenos
- services de API separados
- `controllerAs vm` para organização
- Bootstrap ou CSS simples para produtividade

## 9.4 Entrega
- projeto limpo
- instruções claras para subir
- decisões técnicas justificadas
- observações sobre divergência Vue vs AngularJS registradas

---

# 10. Checklist por exercício

## Exercício 1 — DDL
Checklist:
- [ ] todas as tabelas criadas
- [ ] todas as colunas criadas
- [ ] PK criada
- [ ] FK criada
- [ ] UNIQUE criado
- [ ] NOT NULL aplicado
- [ ] sequences criadas
- [ ] script executa sem erro

## Exercício 2 — DML
Checklist:
- [ ] pelo menos 10 registros por tabela
- [ ] dados coerentes
- [ ] sem violar FK
- [ ] leilões com lotes suficientes para cálculo do total

## Exercício 3 — CRUD REST
Checklist:
- [ ] GET lista
- [ ] GET por id
- [ ] POST
- [ ] PUT
- [ ] DELETE
- [ ] todas as tabelas cobertas
- [ ] testado manualmente

## Exercício 4 — Tela principal
Checklist:
- [ ] menu criado
- [ ] rotas funcionando
- [ ] conteúdo muda conforme URL
- [ ] `#/unidades` funcionando
- [ ] `#/empresas` funcionando
- [ ] `#/leiloes` funcionando

## Exercício 5 — Unidades
Checklist:
- [ ] grid/listagem
- [ ] inclusão
- [ ] edição
- [ ] exclusão
- [ ] integração com API

## Exercício 6 — Empresas consulta
Checklist:
- [ ] listagem somente leitura dos campos pedidos
- [ ] cnpj exibido
- [ ] razão social exibida
- [ ] telefone exibido
- [ ] email exibido
- [ ] ações de editar/excluir acessíveis

## Exercício 7 — Leilões consulta
Checklist:
- [ ] razão social do vendedor
- [ ] início previsto
- [ ] total do leilão
- [ ] cálculo correto
- [ ] formatação adequada

## Exercício 8 — Empresa cadastro/edição
Checklist:
- [ ] rota de inclusão
- [ ] rota de edição
- [ ] carregamento por ID
- [ ] salvar inclusão
- [ ] salvar edição
- [ ] exclusão via tela de consulta
- [ ] obrigatórios validados
- [ ] email validado
- [ ] url validada
- [ ] tamanho máximo validado
- [ ] máscaras aplicadas quando possível

---

# 11. O que escrever no README final

## 11.1 Estrutura sugerida do README
O README final da entrega deve conter:

1. **Visão geral do projeto**
2. **Stack utilizada**
3. **Justificativa da adoção de AngularJS**
4. **Pré-requisitos**
5. **Como subir o banco**
6. **Como executar os scripts SQL**
7. **Como subir a API**
8. **Como subir o front**
9. **Rotas da aplicação**
10. **Endpoints principais**
11. **Validações implementadas**
12. **Observações técnicas**
13. **Melhorias futuras**

## 11.2 Ponto que precisa aparecer no README
Deixar explícito:

- que o enunciado público usava Vue;
- que a implementação foi feita em AngularJS por alinhamento ao ambiente real da AZ;
- que os requisitos funcionais do teste foram mantidos.

---

# 12. Como apresentar o projeto no dia 29/04

## 12.1 Estrutura ideal da apresentação
Apresentar em sequência:

1. contexto do teste;
2. decisão de stack;
3. modelagem do banco;
4. arquitetura do back-end;
5. arquitetura do front-end AngularJS;
6. demonstração das telas;
7. demonstração dos endpoints;
8. validações implementadas;
9. decisões técnicas e trade-offs.

## 12.2 Fala sugerida para abrir a apresentação
> O enunciado público do teste apresentava a camada cliente em Vue, porém, como durante a entrevista foi mencionado que o contexto real da AZ envolve AngularJS, optei por implementar o front-end nessa stack. A ideia foi manter os requisitos funcionais pedidos no teste, mas alinhar a solução ao ambiente real da empresa.

## 12.3 O que o avaliador provavelmente vai observar
- organização
- clareza nas decisões
- aderência ao contexto real
- domínio de CRUD
- domínio de API REST
- domínio de banco relacional
- capacidade de explicar código legado/stack antiga sem travar

## 12.4 Perguntas que podem surgir
### “Por que AngularJS e não Angular moderno?”
Resposta sugerida:
> Porque priorizei aderência ao stack real informado pelo time da AZ. Como o objetivo do teste também é mostrar capacidade de atuar no ambiente da empresa, entendi que AngularJS era a escolha mais estratégica para esta entrega.

### “Por que não seguiu Vue, já que estava no README?”
Resposta sugerida:
> Mantive os requisitos funcionais do README, mas adaptei a tecnologia do front-end para AngularJS por alinhamento ao contexto real apresentado durante a entrevista.

### “O que você melhoraria com mais tempo?”
Resposta sugerida:
> Eu adicionaria testes automatizados, refinaria a experiência visual, melhoraria o tratamento padronizado de erro no front-end e possivelmente componentizaria ainda mais a camada AngularJS.

---

# 13. Erros que você deve evitar

- desenvolver em cima do front Vue original
- deixar a divergência de stack sem explicação
- não documentar como rodar o projeto
- deixar validações fracas na tela de empresa
- calcular total de leilão no front sem necessidade, se o back puder entregar pronto
- usar AngularJS de forma muito bagunçada, com lógica demais no HTML
- não testar os endpoints separadamente
- deixar nomes inconsistentes entre front e back

---

# 14. Definição final de estratégia

## Estratégia oficial
A estratégia oficial deste teste será:

- usar o repositório original **somente como referência**;
- criar uma **solução nova e limpa**;
- implementar **PostgreSQL + Spring Boot + AngularJS**;
- manter todos os requisitos funcionais do enunciado;
- justificar a adoção de AngularJS como **decisão deliberada de aderência ao contexto real da AZ**.

---

# 15. Prompt-base para outras IAs

Abaixo está um prompt completo para colar em outra IA, caso seja necessário continuar o trabalho em outro lugar.

## Prompt
```text
Estou desenvolvendo um teste técnico para a empresa AZ.

Contexto importante:
- O README público do teste descreve a solução com Vue + Spring Boot + PostgreSQL.
- Porém, durante a entrevista com o time da empresa, foi informado de forma confiável que o contexto real deles é AngularJS no front-end.
- Por decisão estratégica, a implementação oficial será feita em AngularJS + Spring Boot + PostgreSQL.
- O objetivo é manter todos os requisitos funcionais do enunciado, mas adaptar a camada cliente para AngularJS por aderência ao ambiente real da empresa.

Quero que você sempre respeite estas decisões:
1. Não sugerir Vue como implementação final.
2. Não sugerir Angular moderno como solução principal.
3. Assumir AngularJS (1.x) como tecnologia oficial do front-end.
4. Assumir Spring Boot no back-end.
5. Assumir PostgreSQL com scripts DDL e DML manuais.
6. Assumir que o projeto final deve ser novo e limpo, usando o repositório original apenas como referência.

Estrutura esperada do projeto:
- database/ddl.sql
- database/dml.sql
- leilao-api/
- leilao-app/
- README.md

O teste pede, em essência:
- DDL completo com tables, columns, PK, FK, unique, sequence e NOT NULL.
- DML com pelo menos 10 registros por tabela.
- CRUD REST de todas as tabelas.
- Tela principal com menu.
- Tela de Unidades com CRUD completo.
- Tela de Empresas com grid/listagem e ações.
- Tela de Leilões com listagem contendo razão social do vendedor, início previsto e total calculado.
- Tela de cadastro/edição de Empresa com validações completas.

Quero respostas técnicas, objetivas e alinhadas ao stack AngularJS + Spring Boot, sempre com foco em boa organização, aderência ao teste e apresentação para avaliação técnica.
```

---

# 16. Conclusão prática

## O que você deve fazer a partir de agora
1. clonar o repositório original apenas para referência;
2. criar um projeto novo seu;
3. montar primeiro `ddl.sql` e `dml.sql`;
4. implementar o back-end completo em Spring Boot;
5. implementar o front-end em AngularJS;
6. documentar a decisão técnica no README;
7. preparar a apresentação com foco em aderência ao stack real da AZ.

## Resposta direta à sua dúvida
**Sim, o `.md` precisava ser alterado**, porque a decisão oficial mudou de **Angular moderno** para **AngularJS**, e isso impacta:

- arquitetura do front-end;
- estrutura de pastas;
- estratégia de implementação;
- justificativa técnica;
- forma de apresentação.

