
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


## Data Model

### JSON Schema

For full details see [jsonschema](jsonschema).

#### Store Schema
```
{
    "$schema" : "http://json-schema.org/draft-03/schema#",
    "id" : "http://json-schema.org/draft-03/schema#",
    "description" : "Store",
    "type" : "object",
    "properties" : {
        "store_id" : { 
            "type" : "string", 
            "required" : true 
        },
        "name" : { 
            "type" : "string", 
            "required" : true 
        },
        "logo_url" : { 
            "type" : "string", 
            "required" : false 
        },
        "message" : { 
            "type" : "string", 
            "required" : false 
        },
        "expire_date" : { 
            "type" : "string", 
             "format" : "date-time",
            "required" : false 
        },
        "mobility_type" : { 
            "type" : "string", 
            "required" : true 
        },
        "phone" : { 
            "type" : "string", 
            "required" : false 
        },
        "default_currency" : { 
            "type" : "string", 
            "required" : true 
        },
        "address" : { 
            "$ref" : "Address.json",
            "required" : false 
        },
        "location" : { 
            "$ref" : "Location.json",
            "required" : true 
        },
        "payment_type" : { 
            "type" : "string",
            "enum" : [ "PPL", "SQR" ],
            "required" : true 
        },
        "gratuity_type" : { 
            "type" : "string", 
            "enum" : [ "STANDARD" ],
            "required" : false 
        }
    },
    "additionalProperties" : false
}
```

#### Location Schema
```
{
    "$schema" : "http://json-schema.org/draft-03/schema#",
    "id" : "http://json-schema.org/draft-03/schema#",
    "description" : "Geographic Location",
    "type" : "object",
    "properties" : {
        "lat" : {
            "description" : "Latitude",
            "type" : "number",
            "required" : true ,
            "minimum" : -90.00,
            "maximum" : 90.00
        },
        "lon" : {
            "description" : "Longitude",
            "type" : "number",
            "required" : true ,
            "minimum" : -180.00,
            "maximum" : 180.00
        }
    },
    "additionalProperties" : false
}
```

### Sample JSON Instance

```
{
  "name" : "Acme",
  "location" : {
    "lat" : 40.83638083615808,
    "lon" : -73.84744890687338
  },
  "store_id" : "923ab197",
  "default_currency" : "USD",
  "payment_type" : "SQR",
  "mobility_type" : "MOBILE",
  "phone" : "2121239876",
  "expire_date" : "2013-09-11T23:39:02+0000"
}
```

## REST Resources

### GET stores/v1/stores/{STORE_ID}

Return a store.

### PUT stores/v1/stores/{STORE_ID}

Add or update a store.

### DELETE stores/v1/stores/{STORE_ID}

Delete a store.

## TODO
* Logging - move to logback instead of log4j

