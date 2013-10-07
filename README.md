
# Sample REST JAX-RS CXF-based API

Template for best practices for REST JAX-RS CXF Spring.

## Description

Sample app that adds, retrieves and deletes a store.
* GET - get store
* PUT - add or update store with client-provided ID
* DELETE - delete store


## Modules

* [stores-webapp](stores-webapp/README.md) - API web server
* [stores-api-client](stores-api-client/README.md) - API service client 
* [stores-api-dto](stores-api-dto/README.md) - API DTO objects shared by server and client
* [common-jaxrs](common-jaxrs/README.md) - Common HTTP and JAX-RS utilities
* [common-util](common-util/README.md) - Common utilities
* [common-jetty](common-jetty/README.md) - Standalone embedded Jetty WAR launcher


## Building 

The default build will create a WAR with the mock provider (currently only one, MongoDB shortly to follow).

    mvn clean install

To toggle service providers - where serviceProvider can be mock-provider (default), http-provider or javaremoting-provider

    cd stores-webapp
    mvn clean install -DserviceProvider=$serviceProvider 


## Running Server

The build creates target/stores-webapp/target/stores.war which you can either:

* Run inside a web server such Tomcat or Jetty
* Run in standalone mode using embedded Jetty: run-war.sh

## Running sample curl client scripts

* cd [stores-webapp/curl](stores-webapp/curl/README.md) 
* put.sh $myStoreId put.json
* delete.sh $myStoreId 
* get.sh $myStoreId 

## TODO
* Logging - move to logback instead of log4j

