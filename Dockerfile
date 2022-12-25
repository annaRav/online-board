#FROM openjdk:11-oracle
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#EXPOSE 8080

FROM maven:3.6.3-jdk-11 as MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -DskipTests
FROM openjdk:11-oracle
COPY --from=MAVEN_BUILD target/online-board-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
