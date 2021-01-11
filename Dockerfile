FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/vipulgupta1106/football-test.git

FROM maven:3.5-jdk-8-alpine
WORKDIR /app
COPY --from=0 / /app
