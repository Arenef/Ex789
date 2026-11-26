# Stage 1: Build WAR with Maven + JDK 21
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -Dmaven.compiler.source=21 -Dmaven.compiler.target=21 -B

# Stage 2: Run on Apache Tomcat 10.1 + JDK 21
FROM tomcat:10.1-jdk21-temurin
RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build /app/target/Cookies-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
