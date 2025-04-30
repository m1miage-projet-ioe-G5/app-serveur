# Étape de build - Utilisation de cache Maven optimisé
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn clean package -DskipTests -T 1C

# Étape de production ultra-légère
FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="devops@moovly.com"

ENV TZ="Europe/Paris" \
    SPRING_PROFILES_ACTIVE="prod" \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -XX:+AlwaysPreTouch -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Djava.security.egd=file:/dev/./urandom" \
    STARTUP_TIMEOUT=30

# Configuration sécurisée Alpine
RUN addgroup -S javauser && \
    adduser -S -G javauser javauser && \
    mkdir -p /app && \
    chown -R javauser:javauser /app

COPY --from=build --chown=javauser:javauser /workspace/target/*.jar /app/application.jar

USER javauser
WORKDIR /app

EXPOSE 8080

HEALTHCHECK --start-period=10s --interval=5s --timeout=2s --retries=3 \
    CMD wget -qO- http://localhost:8080/actuator/health || exit 1

# Script de démarrage optimisé
COPY --chown=javauser:javauser entrypoint.sh /app/
RUN chmod +x /app/entrypoint.sh

ENTRYPOINT ["/app/entrypoint.sh"]
