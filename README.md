# SMESH - A Simple Mesh Server

Simple Mesh Server simple to run, configure and deploy. Uses common well documented components and libraries from the Java ecosystem. Acts as a sidecar to services and software.

## Components
The server is built using Hazelcast providing the data plane, with a Spring Boot server and Spring integration providing the glue between the mesh and the remote service.

## Building
Requires Java 11
`./gradlew buld`

## Running
`java --add-modules java.se --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED   --add-opens java.base/java.nio=ALL-UNNAMED   --add-opens java.base/sun.nio.ch=ALL-UNNAMED   --add-opens java.management/sun.management=ALL-UNNAMED   --add-opens jdk.management/com.ibm.lang.management.internal=ALL-UNNAMED   --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED -jar smesh-server-0.1.0-SNAPSHOT.jar`

## Configuration

Configuration of a SMESH node is enabled through spring profiles and configuration within an `application.yml` file, spring profile specific application.yml files.

### Clustering

### HTTP Client

### Configuring a service 

SMESH is designed to be a sidecar application.

#### Spring Integration Service