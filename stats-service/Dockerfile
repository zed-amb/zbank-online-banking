# Use OpenJDK base image
FROM eclipse-temurin:17-jdk-alpine

# Create app directory
WORKDIR /app

# Copy JAR into container
COPY target/stats-service-*.jar app.jar

# Expose port
EXPOSE 8081

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
