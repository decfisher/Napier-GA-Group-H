FROM openjdk:latest
COPY ./target/GroupH-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "GroupH-0.1.0.1-jar-with-dependencies.jar"]