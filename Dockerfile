#FROM openjdk:8
#EXPOSE 8085
#ARG JAR_FILE=target/my-app.jar
#COPY ${JAR_FILE} /usr/local/service
#WORKDIR /usr/local/service
#ENTRYPOINT ["java","-jar","/my-app.jar"]


FROM maven:latest AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM openjdk:8
EXPOSE 8085
ARG JAR_FILE=/home/app/target/*.jar
COPY ${JAR_FILE} my-app.jar
ENTRYPOINT ["java","-jar","/my-app.jar"]

