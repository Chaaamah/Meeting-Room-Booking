<<<<<<< HEAD
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mkdir -p /app/.m2 && mvn -Dmaven.repo.local=/app/.m2 clean package -DskipTests

FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
=======
FROM eclipse-temurin:17-jdk

VOLUME /tmp

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
>>>>>>> 13d93b99e182f4193c18721e8ea5bf5e81b2f120
