FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/bazar-0.0.1.jar
COPY ${JAR_FILE} bazar.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bazar.jar"]