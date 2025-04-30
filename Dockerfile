FROM maven AS build
WORKDIR /moovly.server
COPY . .
RUN mvn clean install

FROM adoptopenjdk/openjdk22 AS deploy
LABEL description="Java 22 Docker image build to run moovly server"

ARG JAR_FILE="/moovly.server/server/target/Projet-IOE-Moovly.jar"
ENV TZ="Europe/Paris"
ENV spring_profiles_active="dev"

COPY --from=build ${JAR_FILE} /opt/app/app.jar
RUN chmod +x /opt/app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
