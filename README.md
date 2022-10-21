# Java Spring Boot Service Blueprint
![Build Status](https://github.com/tblasche/blueprint-java-spring-boot/workflows/Build/badge.svg)
![License](https://img.shields.io/github/license/tblasche/blueprint-java-spring-boot)
[![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=tblasche_blueprint-java-spring-boot&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tblasche_blueprint-java-spring-boot)

Blueprint of a Spring Boot Service with enterprise features like JSON logging, Swagger UI or Prometheus Metrics included.

## Tech Info
* Language: Java
* Build System: Gradle
* Containerization: Docker
* Framework: Spring Boot
* Testing: Spock, Spring MockMvc
* Template Engine: Thymeleaf
* Metrics: Prometheus
* API Documentation: Swagger UI via [springdoc](https://springdoc.org/)
* Logging: Access and Application Logs in JSON format
* Code Coverage: JaCoCo Report
* CI/CD: GitHub Actions
* Static Code Analysis via SonarCloud

## Quick Start
* Start application and find API docs at http://localhost:8080/apidoc
  ```console
  $ ./gradlew bootRun
  ```
* Run tests
  ```console
  $ ./gradlew clean test
  ```
* Generate Code Coverage Report. HTML Report can be found in `./build/reports/jacoco/test/html`
  ```console
  $ ./gradlew check jacocoTestReport
  ```
* Build docker image via gradle and run docker container via `docker compose`. Find API docs at http://localhost:8080/apidoc
  ```console
  $ ./gradlew bootBuildImage --imageName=tblasche/blueprint-java-spring-boot
  $ docker compose up
  ```
* Check for updated dependencies via [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)
  ```console
  $ ./gradlew dependencyUpdates -Drevision=release
  ```

## API Documentation
Swagger UI is accessible via `/apidoc`
