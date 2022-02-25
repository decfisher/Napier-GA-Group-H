FROM openjdk:latest
COPY ./target/Napier-GA-Group-H-0.1.0.3-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Napier-GA-Group-H-0.1.0.3-jar-with-dependencies.jar"]