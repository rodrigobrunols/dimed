FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/*.jar dimed.jar
ENTRYPOINT ["java","-jar","/dimed.jar"]
