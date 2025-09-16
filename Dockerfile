# Use Eclipse Temurin JDK 17 as base image
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy built jar file
COPY target/*.jar app.jar

# Expose port 8080 for Spring Boot
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
