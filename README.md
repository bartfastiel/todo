# todo

Minimalistic todo API with MongoDB data storage

## Usage

Start the application with the following settings to connect to a remote mongo database

```
-Dspring.data.mongodb.uri=mongodb+srv://<user>:<pwd>@<mongoserver>/<database> \
-Dspring.data.mongodb.database=<database>
```

Or store the settings in `src/main/resources/application-secret.properties` for local testing.

## Clean code

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=bartfastiel_todo&metric=bugs)](https://sonarcloud.io/summary/new_code?id=bartfastiel_todo)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=bartfastiel_todo&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=bartfastiel_todo)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=bartfastiel_todo&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=bartfastiel_todo)

![build](https://github.com/bartfastiel/todo/actions/workflows/maven.yml/badge.svg)

