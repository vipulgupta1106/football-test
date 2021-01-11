FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/vipulgupta1106/football-test.git

FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from=0 /app /app
CMD ["pwd"]
RUN mvn clean install

FROM openjdk:8
WORKDIR /app
EXPOSE 8085
COPY --from=1 /app/target/my-app.jar /app
CMD ["java -jar my-app.jar"]