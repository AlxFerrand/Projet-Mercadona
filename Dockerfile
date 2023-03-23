FROM maven:3.9.0-eclipse-temurin-17
WORKDIR /app
COPY . .
RUN mvn install -DskipTests
CMD mvn spring-boot:run

#FROM eclipse-temurin:17-jdk
#ARG JAR_FILE=target/Mercadona23-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/app.jar"]