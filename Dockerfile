FROM openjdk:8
EXPOSE 8085
ARG JAR_FILE=target/my-app.jar
WORKDIR /app
COPY ${JAR_FILE} /app
ENTRYPOINT ["java","-jar","/my-app.jar"]
