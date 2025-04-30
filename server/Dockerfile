# Build stage
FROM maven:3.8.6-eclipse-temurin-17 AS builder

WORKDIR /workspace
# Copy all POMs first for better cache utilization
COPY pom.xml .
COPY rest-api/pom.xml rest-api/pom.xml
COPY server/pom.xml server/pom.xml

# Download dependencies
RUN mvn -B dependency:go-offline

# Copy sources
COPY rest-api/src rest-api/src
COPY server/src server/src

# Build only the server module but include dependencies
RUN mvn -B package -pl server -am -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /workspace/server/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]