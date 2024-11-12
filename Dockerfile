FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

WORKDIR /app
FROM eclipse-temurin:21-alpine
COPY --from=build /build/target/api-clima-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
