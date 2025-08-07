# -------- Stage 1: Build --------
FROM maven:3.9.9-amazoncorretto-17-alpine AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# -------- Stage 2: Runtime --------
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

## local mvn build
#FROM amazoncorretto:17-alpine
#WORKDIR /app
#COPY target/*.jar app.jar
#EXPOSE 8081
#ENTRYPOINT ["java", "-jar", "app.jar"]