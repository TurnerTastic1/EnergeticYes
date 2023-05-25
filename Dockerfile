FROM openjdk:20-jdk-slim

WORKDIR /app
COPY ./target/EnergeticYes-1.0.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "EnergeticYes-1.0.0-SNAPSHOT.jar"]