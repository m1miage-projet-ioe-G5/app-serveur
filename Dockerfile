# Étape de build
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY . .
RUN mvn clean package -DskipTests

# Étape de production
FROM eclipse-temurin:17-jre-alpine
LABEL maintainer="ibrahimahollan2020@gmail.com.com"

ENV TZ="Europe/Paris" \
    SPRING_PROFILES_ACTIVE="prod" \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

RUN addgroup -S javauser && \
    adduser -S -G javauser javauser && \
    mkdir -p /app && \
    chown -R javauser:javauser /app

# Correction : chemin exact du JAR avec le bon nom
COPY --from=build --chown=javauser:javauser /workspace/server/target/Projet-IOE-Moovly.jar /app/app.jar

USER javauser
WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java ${JAVA_OPTS} -jar /app/app.jar"]
