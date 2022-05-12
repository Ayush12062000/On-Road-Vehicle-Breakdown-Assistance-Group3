FROM openjdk:11
LABEL maintainer="Ayush Kesarwani"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","application.jar"]