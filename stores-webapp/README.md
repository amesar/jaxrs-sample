
# Stores Webapp server.

## Overview

  * Implemented with Spring and CXF JAX-RS
  * Uses codahale Metrics

## Scripts

  * build-war.sh $povider - builds WAR - where provider can be: mongodb-provider | mock-provider (default)
  * run-war.sh - runs WAR in standalone mode
  * [curl](curl/README.md) - curl test scripts

## TODO

  * Get Metrics annotations working
