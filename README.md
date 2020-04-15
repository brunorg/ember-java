# Ember - Java

This README outlines the details of collaborating on this Ember application.
A short introduction of this app could easily go here.

```Markdown
    It was a long time since I wrote this code. On that time Ember already was a mature framework, while React had just been born.
    Now this project is outdated, Ember received a lot of changes and new features, moreover, on Java land both JEE and Spring mainly the last with spring-boot made the developer life less complicated ( well, to do more and write less code ).

    Having said that, I think its a great oportunit to rewrite this code to a new version and using other technologies in Java side. So you can wait for a new version using this technologies:

    - Ember cli
    - Spring Boot
    - MongoDB
    - Docker
```

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](https://git-scm.com/)
* [Node.js](https://nodejs.org/) (with npm)
* [Ember CLI](https://ember-cli.com/)
* [Google Chrome](https://google.com/chrome/)
* [Docker](https://docs.docker.com/get-started/)

## Installation

* `git clone <repository-url>` this repository
* `cd ember-java`
* `yarn install`

## Running / Development

### 1. Running Mongo Database with Docker

If swarm is not running, run this command first:

```bash
$ docker swarm init
...
... done
```

Then, start the mongo stack:

```bash
$ docker stack deploy --compose-file docker-compose.yml mongo
...
... done
```

### 2. Running the spring-boot java backend server

```bash
$ mvn springboot:run
[INFO] Scanning for projects...
...
... Started Application in 5.216 seconds ...
```

### 3. Running the Ember Server

```bash
$ ember serve
⠏ building...
Build successful (15309ms) – Serving on http://localhost:4200/
```

### 4. Accessing the application

Now you can access the application on http://localhost:4200/

On login page enter the login: `tomster@emberjs.com` and password: `test`

### 5. Creating other user

You can add other user for test using the command

```bash
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "other_email@hostname.com",
    "password": "password"
}' http://localhost:8080/api/users/sign-up
```

## More information: 

### Code Generators

Make use of the many generators for code, try `ember help generate` for more details

### Running Tests

* `ember test`
* `ember test --server`

### Linting

* `npm run lint:hbs`
* `npm run lint:js`
* `npm run lint:js -- --fix`

### Building

* `ember build` (development)
* `ember build --environment production` (production)

### Deploying

Specify what it takes to deploy your app.

## Further Reading / Useful Links

* [ember.js](https://emberjs.com/)
* [ember-cli](https://ember-cli.com/)
* Development Browser Extensions
  * [ember inspector for chrome](https://chrome.google.com/webstore/detail/ember-inspector/bmdblncegkenkacieihfhpjfppoconhi)
  * [ember inspector for firefox](https://addons.mozilla.org/en-US/firefox/addon/ember-inspector/)
