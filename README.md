# todo

Minimalistic todo API with MongoDB data storage

See it in action at: http://bartfastiel-todo.schwarz-consulting.de/

## Build it

Prerequisites: `Maven`

```shell
mvn clean package
```

## Run it

Prerequisites: `Java JDK 17`, `MongoDB`

```shell
java -Dserver.port=<port> \
     -Dspring.data.mongodb.uri=<mongodb uri> \
     -Dspring.data.mongodb.database=<database name> \
     -jar app.jar
```

Replace `<port>` with the local port that the app will be listening to.

Replace `spring.data.mongodb.uri` with a mongo db url (i.e. in the
format `mongodb+srv://<user>:<pwd>@<mongoserver>/<database>`)

Replace `spring.data.mongodb.database` with a database name, i.e. `todo`

## Clean code

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=bartfastiel_todo&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=bartfastiel_todo)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=bartfastiel_todo&metric=coverage)](https://sonarcloud.io/summary/new_code?id=bartfastiel_todo)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=bartfastiel_todo&metric=bugs)](https://sonarcloud.io/summary/new_code?id=bartfastiel_todo)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=bartfastiel_todo&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=bartfastiel_todo)

![build](https://github.com/bartfastiel/todo/actions/workflows/maven.yml/badge.svg)

