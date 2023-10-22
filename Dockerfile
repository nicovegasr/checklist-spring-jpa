FROM openjdk:17-jdk-slim-buster
LABEL authors="Nicolas Vegas Rodriguez"

WORKDIR /checklist-backend
VOLUME /tmp
EXPOSE 8080

COPY /target/checklist-0.0.1-SNAPSHOT.jar checklist.jar
CMD ["java", "-jar", "checklist.jar"]
