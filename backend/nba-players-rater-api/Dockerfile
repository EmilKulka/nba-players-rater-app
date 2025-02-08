FROM maven:3.8.5-openjdk-17 AS builder

ARG SPRING_DATASOURCE_URL
ARG POSTGRES_USER
ARG POSTGRES_PASSWORD

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

RUN apt-get update && apt-get install -y curl

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]