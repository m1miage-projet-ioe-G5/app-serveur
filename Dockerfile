# Étape de build
FROM eclipse-temurin:22-jdk-jammy as builder
WORKDIR /workspace/app-serveur

# Copie des fichiers pom.xml et source
COPY pom.xml .
COPY src src

# Build avec Maven en excluant les tests
RUN ./mvnw clean package -DskipTests

# Étape d'exécution
FROM eclipse-temurin:22-jre-jammy
VOLUME /tmp
WORKDIR /app

# Copie du JAR depuis l'étape de build
COPY --from=builder /workspace/app-serveur/target/*.jar app.jar

# Configuration pour Render
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app-serveur/app.jar"]

EXPOSE 8081
