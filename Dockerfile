FROM gradle:9.0.0-jdk24-alpine AS builder
WORKDIR /app
COPY build.gradle ./
COPY src/main ./src/main
RUN gradle build --no-daemon

FROM eclipse-temurin:24-jre-alpine
WORKDIR /app
RUN adduser -D appuser && chown -R appuser /app
COPY --from=builder /app/build/libs/app.jar app.jar
USER appuser
ENTRYPOINT ["java", "-jar", "app.jar"]
