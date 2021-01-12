FROM openjdk:8
EXPOSE 8085
#ARG JAR_FILE=/var/lib/jenkins/workspace/test-2/target/my-app.jar
COPY target/find-standings-project.jar find-standings-project.jar
ENTRYPOINT ["java","-jar","find-standings-project.jar"]
#
#
#FROM maven:3.5.2-jdk-8 AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean install
#
#FROM openjdk:8
#EXPOSE 8085
#ARG JAR_FILE=/home/app/target/*.jar
#COPY ${JAR_FILE} my-app.jar
#ENTRYPOINT ["java","-jar","/my-app.jar"]

