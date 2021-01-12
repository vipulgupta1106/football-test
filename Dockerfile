FROM openjdk:8
EXPOSE 8085
ARG JAR_FILE=target/my-app.jar
COPY ${JAR_FILE} /usr/local/service
WORKDIR /usr/local/service
ENTRYPOINT ["java","-jar","/my-app.jar"]
