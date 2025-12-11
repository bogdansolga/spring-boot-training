# Spring Boot Training

Examples for a 4-day Spring and Spring Boot training, using **Spring Boot 4.0**.

## Prerequisites

- Java 21+
- Maven 3.9+

## Building the Project

Build all modules from the root directory:

```bash
mvn clean install
```

To build a specific module with its dependencies:

```bash
mvn clean install -pl d03/d03s05-project-packaging/d03s05s01-jar-packaging --also-make
```

## Day 1 - Spring Core

- **d01s01** - Configuration modes (annotations, XML)
- **d01s02** - Simple beans wiring
- **d01s03** - Bean attributes and parameters (lifecycle, aliasing)
- **d01s04** - Component scanning and qualifying
- **d01s05** - Bean scopes and profiles

## Day 2 - Spring Boot Fundamentals

- **d02s01** - Spring Boot intro
- **d02s02** - Properties and profiles (YAML, config override)
- **d02s03** - Spring Web (REST, OXM, static content)
- **d02s04** - Exception handling
- **d02s05** - Database access (embedded, real DB, Spring Data JDBC/REST)

## Day 3 - Security, Testing & Operations

- **d03s01** - Conditional annotations and configuration properties
- **d03s02** - Spring Security
- **d03s03** - Unit and integration testing
- **d03s04** - Spring Boot Actuator
- **d03s05** - Project packaging (JAR, WAR)

## Day 4 - Advanced Topics

- **d04s01** - Async processing (presentation/service layer, task executors, messaging)
- **d04s02** - Scheduled tasks (non-clustered, clustered)
- **d04s03** - Events pub/sub
- **d04s04** - Spring Retry
- **d04s05** - Caching
- **d04s06** - Complete project
