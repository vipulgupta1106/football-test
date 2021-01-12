FROM openjdk:8
EXPOSE 8085
COPY target/find-standings-project.jar find-standings-project.jar
ENTRYPOINT ["java","-jar","find-standings-project.jar"]
