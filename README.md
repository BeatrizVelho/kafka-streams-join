# Introduction

This project has code to join two streams. Since joins are made exclusively by the record's key, a rekey mechanism was implemented, and to be used, the following properties should be added to the `resources/streams.properties` file:
- input_one.join.field (Field name of the first topic to be used in the join)
- input_one.new.topic (Name of the topic to save the records with the new key)
- input_two.join.field (Field name of the second topic to be used in the join)
- input_two.new.topic (Name of the topic to save the records with the new key)


## Start the Kafka broker

From a directory containing the `docker-compose.yml` file, run this command to start all services in the correct order.

```
docker compose up -d
```

This command will bring up:

- Zookeeper
- Kafka broker on localhost:9092
- Confluent Control Center on http://localhost:9021
- ksql Server on localhost:8088
- Connect server on localhost:8083
- Schema Registry on localhost:8081
- Rest-proxy
- ksql cli

## Build
To build the project follow these 2 steps:

1. Open the terminal.

2. Run these sequence of commands:

```sh
mvn clean

mvn compile
```

## Stop the Kafka broker

Once you’ve finished, you can shut down the Kafka broker. Note that doing this will destroy all messages in the topics that you’ve written.

From a directory containing the `docker-compose.yml` file, run this command to stop all services in the correct order.

```
docker-compose down
```

We should also remove all created docker volumes with:
```
 docker volume rm $(docker volume ls -q)
```

## Improvements 

- Add authentication and authorization (SSL + SASL)
- Missing validations