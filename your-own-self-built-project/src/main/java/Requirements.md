Implement a project that is managing Sections and:
- uses a three-layer architecture using the Spring @Component model annotations
  - @Controller
  - @Service
  - @Repository
- uses two development environments:
  - dev
  - pre

Optionally:
- use the bean parameters (init and destroy methods)
- use the @PostConstruct and @PreDestroy annotations
- use a prototype bean scope

Later:
- loads two configuration files:
  - dev.properties
  - pre.properties
- checks the usage of a profile during execution