FROM openjdk:latest
COPY ./target/seMethods-Group-H.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods-Group-H.jar", "db:3306", "30000"]