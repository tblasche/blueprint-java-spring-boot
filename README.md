# Java Spring Boot Service Blueprint
[![Build Status](https://github.com/tblasche/blueprint-java-spring-boot/actions/workflows/main.yml/badge.svg)](https://github.com/tblasche/blueprint-java-spring-boot/actions/workflows/main.yml)
[![License](https://img.shields.io/github/license/tblasche/blueprint-java-spring-boot)](https://github.com/tblasche/blueprint-java-spring-boot/blob/main/LICENSE)
[![Code Coverage](https://sonarcloud.io/api/project_badges/measure?project=tblasche_blueprint-java-spring-boot&metric=coverage)](https://sonarcloud.io/summary/new_code?id=tblasche_blueprint-java-spring-boot)

Blueprint of a Spring Boot Service with enterprise features like JSON logging, Swagger UI or Prometheus Metrics included.

## Tech
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)

The service...
* is `docker`'ized
* utilizes `Spring MockMvc` and `Spock` for testing
* provides `Prometheus` metrics
* provides `Swagger UI` and `OpenAPI Specification` via [springdoc](https://springdoc.org/)
* writes `access and application logs` in `JSON` format
* uses `Thymeleaf` as Template Engine
* utilizes `GitHub Actions` for CI/CD
* performs static code analysis via `SonarCloud`
* checks for vulnerable dependencies via `OWASP Dependency Check`

## Run locally
* Native: http://localhost:8080/apidoc
  ```console
  ./gradlew bootRun
  ```
* Via Docker: http://localhost:8080/apidoc
  ```console
  docker compose up --build
  ```

## API Docs / Swagger UI
Find API docs at `/apidoc`

## Common Actions
* Start application and find API docs at http://localhost:8080/apidoc
  ```console
  ./gradlew bootRun
  ```
* Run tests
  ```console
  ./gradlew clean test
  ```
* Generate Code Coverage Report. HTML report can be found in `./build/reports/jacoco/test/html`
  ```console
  ./gradlew check jacocoTestReport
  ```
* Build docker image and run via `docker compose`. Find API docs at http://localhost:8080/apidoc
  ```console
  docker compose up --build
  ```
* Check for outdated dependencies via [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)
  ```console
  ./gradlew dependencyUpdates -Drevision=release
  ```
* Check for vulnerable dependencies via [OWASP Dependency-Check Plugin](https://jeremylong.github.io/DependencyCheck/dependency-check-gradle/index.html). HTML report can be found in `./build/reports/dependency-check-report.html`
  ```console
  ./gradlew dependencyCheckAnalyze
  ```
* Update gradle wrapper to latest gradle version
  ```console
  ./gradlew wrapper --gradle-version latest
  ```
