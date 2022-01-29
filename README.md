# todo

Minimalistic todo API with MongoDB data storage

## Usage

Start the application with the following settings to connect to a remote mongo database

```
-Dspring.data.mongodb.uri=mongodb+srv://<user>:<pwd>@<mongoserver>/<database> \
-Dspring.data.mongodb.database=<database>
```

Or store the settings in `src/main/resources/application-secret.properties` for local testing.
