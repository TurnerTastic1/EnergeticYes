FROM openjdk:20

EXPOSE 8080

WORKDIR /app
COPY ./target/EnergeticYes-1.0.0-SNAPSHOT.jar /app


CMD ["java", "-jar", "EnergeticYes-1.0.0-SNAPSHOT.jar"]