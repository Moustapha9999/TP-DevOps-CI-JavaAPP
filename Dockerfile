
## Étape de build
#FROM maven:3.9.11-openjdk-17 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

## Étape de production avec Tomcat
#FROM tomcat:10.1-jdk21-temurin
#RUN rm -rf /usr/local/tomcat/webapps/*
#COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
#EXPOSE 8080
#CMD ["catalina.sh", "run"]

# Étape 1 : build avec Maven
FROM maven:3.9.11-eclipse-temurin AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : runtime avec Tomcat
FROM tomcat:10.1-jdk21
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]