FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/fse-0.0.1-SNAPSHOT.jar company.jar
ENTRYPOINT ["java","-jar","/company.jar"]