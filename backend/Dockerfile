FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY . .
ARG SERVICE=peopleai-api-gateway
RUN mvn -B -pl ${SERVICE} -am -DskipTests package
FROM eclipse-temurin:17-jre
WORKDIR /app
ARG SERVICE=peopleai-api-gateway
COPY --from=build /workspace/${SERVICE}/target/${SERVICE}-1.0.0.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
