FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/vipulgupta1106/football-test.git

FROM maven:3.5.2-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM openjdk:8
EXPOSE 8085
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} my-app.jar
ENTRYPOINT ["java","-jar","/my-app.jar"]
