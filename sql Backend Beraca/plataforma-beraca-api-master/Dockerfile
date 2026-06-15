# Paso 1: Compilar la aplicación usando Maven y Java 17
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Paso 2: Ejecutar la aplicación usando un entorno ligero de Java
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
