FROM openjdk:21-jre-slim
WORKDIR /app
COPY target/OnlineTraining-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java", "-jar", "app.jar"]
