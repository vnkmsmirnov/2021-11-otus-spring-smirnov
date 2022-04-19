FROM openjdk:11-jre-slim
MAINTAINER User "vnk.m.smirnov@gmail.com"
COPY ./target/library-0.0.1.jar ../app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
