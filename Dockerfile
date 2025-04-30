# Étape de build
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY . .
RUN mvn clean package -DskipTests

# Étape de production
FROM eclipse-temurin:17-jre-jammy
LABEL maintainer="ibrahimaholland2020@gmail.com" \
      description="Application Moovly en production"

ENV TZ="Europe/Paris" \
    SPRING_PROFILES_ACTIVE="prod" \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -XX:+HeapDumpOnOutOfMemoryError"

# Configuration sécurisée
RUN addgroup --system javauser && \
    adduser --system --ingroup javauser javauser && \
    mkdir -p /app && \
    chown -R javauser:javauser /app

COPY --from=build --chown=javauser:javauser /workspace/server/target/*.jar /app/application.jar

USER javauser
WORKDIR /app

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/application.jar"]
