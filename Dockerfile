# Étape de build
FROM eclipse-temurin:22-jdk-jammy AS builder
WORKDIR /workspace

# Copier le projet complet (pom parent + modules)
COPY . .

# Construction uniquement du module 'serveur' (le module exécutable)
RUN ./mvnw clean package -pl server -am -DskipTests

# Étape d'exécution
FROM eclipse-temurin:22-jre-jammy
VOLUME /tmp
WORKDIR /app

# Copie du JAR généré pour le module 'serveur'
COPY --from=builder /workspace/server/target/*.jar app.jar

# Configuration d'environnement
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar"]

EXPOSE 8080
