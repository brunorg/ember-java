# Re-construção do projeto

A ideia desse documento é detalhar o processo de reconstrução do projeto inicial tomando como base o código legado movido para as pastas _legacy_app (frontend ember)_ e _legacy_java (backend java)_.

O projeto será recriado com base no conteúdo antigo mas usando o que há de mais novo nas versões atuais dos frameworks quando esse projeto está sendo atualizado,assim é possível pra quem está acompanhando esse projeto entender como é o processo de criação de uma aplicação no Ember.js que mudou bastante desde então.

Para se ter uma ideia, a versão inicial utilizava ferramentas em Ruby para o desenvolvimento e build do aplicativo e nem existia a ferramenta **ember-cli** naquela época. Hoje, o build do frontend é baseado no próprio ecossistema do **NodeJS** e o framework está ainda mais maduro. No lado do **Java** também há uma grande evolução ao adotar o **Spring Boot** ao invés do JEE, grande parte do código será removido devido as convenções e facilitadores do Spring.

## primeros passos

Iniciamos com o antigo template do application.hbs e extraindo a parte da barra de loading através da criação um componente de loading.

```bash
$ ember generate component loading-bar
installing component...
```

Faremos o mesmo para as mensagens de notificações

```bash
$ ember generate component message-notifications
installing component...
```

E também para a barra de navegação

```bash
$ ember generate component navigation-bar
installing component...
```

Para exibir propriedades da aplicação em geral precisamos criar um controller

```bash
$ ember generate controller application
installing controller...
```

Iniciamos com a criação da tela de clientes, produtos e pedidos, a primeira ação foi criar uma rota usando o ember-cli

```bash
$ ember generate route customers
installing route...

$ ember generate route products
installing route...

$ ember generate route orders
installing route...
```

## Tela de cliente

Na versão antiga do ember-java as telas eram separadas por rotas bem específicas seguindo esse padrão.

| Rotas         | Tela                                      |
|---------------|-------------------------------------------|
| /customers    | Lista de clientes                         |
| /customer/:id | Lista cliente específico                  |
| /customer     | Tela em branco pra cadastrar novo cliente |

Na nova versão será usado REST padrão, portanto a mesma URL **/customers** servirá tanto para listar os clientes quanto para cadastrar um novo cliente isso facilitará o desenvolvimento das telas e rotas.

Antes de integrar com o backend do Spring Boot iremos fazer o uso da biblioteca mirage para simular/mockar a integração com uma API, pra isso é necessário instalar o Add On do mirage e um adapter para acessá-lo na aplicação.

```bash
ember install ember-cli-mirage

ember generate adapter application
```

Após configurar os adaptadores para a API vamos criar um Model que representa um cliente.

```bash
ember g model customer
ember g model order
```

OBS: Tive que trocar o Spring Data Rest por outra API baseada na especificação JSON:API que possui melhor compatibilidade com o Ember Data, enquanto
que o padrão utilizado pelo Spring Data Rest é o formato HAL e não existe Adapter do Ember para esse formato.
