# Étape de build
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY . .
RUN mvn clean package spring-boot:repackage -DskipTests

# Étape de production
FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="ibrahimaholland2020@gmail.com"

ENV TZ="Europe/Paris" \
    SPRING_PROFILES_ACTIVE="prod"

RUN addgroup -S javauser && \
    adduser -S -G javauser javauser && \
    mkdir -p /app && \
    chown -R javauser:javauser /app

COPY --from=build --chown=javauser:javauser /workspace/target/*.jar /app/app.jar

USER javauser
WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
