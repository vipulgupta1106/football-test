FROM openjdk:8
EXPOSE 8085
COPY target/find-standings-project.jar find-standings-project.jar
CMD ["java -jar"]
ENTRYPOINT ["find-standings-project.jar"]
