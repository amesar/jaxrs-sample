
# Sample REST JAX-RS CXF-based API

Template for best practices for REST JAX-RS CXF Spring.

## Description

Sample app that adds, retrieves and deletes a store from a non-persistent in-memory data store.

* PUT - add or update store with client-provided ID
* GET - get store
* DELETE - delete store

## Features

* Multi-module maven project
* Uses CXF JAX-RS framework
* curl scripts to manipulate REST API
* Uses Spring XML for dependency injection
* Exposes yammer metrics as JMX bean
* Exposes log4j configuration at runtime as JMX bean

## Modules

* [stores-webapp](stores-webapp/README.md) - API web server
* [stores-api-client](stores-api-client/README.md) - API client 
* [stores-api-dto](stores-api-dto/README.md) - API DTO objects shared by server and client
* [common-jaxrs](common-jaxrs/README.md) - Common HTTP and JAX-RS utilities
* [common-util](common-util/README.md) - Common utilities
* [common-jetty](common-jetty/README.md) - Standalone embedded Jetty WAR launcher

## Building 

The default build will create a WAR with the mock provider (currently only one, MongoDB shortly to follow).

    mvn clean install

## Running Server

The build creates target/stores-webapp/target/stores.war which you can either:

* Run inside a web server such Tomcat or Jetty
* Run in standalone mode using embedded Jetty: run-war.sh

## Running sample curl client scripts

* cd [stores-webapp/curl](stores-webapp/curl/README.md) 
* put.sh $myStoreId put.json
* get.sh $myStoreId 
* delete.sh $myStoreId 

Example

```
put.sh 505 put.json
get.sh 505 
```


## TODO
* Logging - move to logback instead of log4j

