## Use Maven with JDK 21
#FROM maven:3.9.6-eclipse-temurin-21 AS build
#
## Set working directory
#WORKDIR /app
#
## Copy files from the task2 directory
#COPY task2/pom.xml .
#COPY task2/src ./src
#
## Build the project (this will create the jar file)
#RUN mvn clean install
#
## Copy testng.xml file if it's not inside the project already
#COPY task2/src/test/resources/testng.xml ./src/test/resources/testng.xml
#
## Run the Spring Boot application and the TestNG tests sequentially
#CMD java -jar target/task2-0.0.1-SNAPSHOT.jar & mvn test -DsuiteXmlFile=src/test/resources/testng.xml
