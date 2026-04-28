# Step 1: Use a lightweight JDK 17 runtime
FROM eclipse-temurin:17-jre-alpine

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy the generated jar from the target folder
# Your Maven build creates this in the 'target' directory
COPY target/*.jar app.jar

# Step 4: Expose the default Spring Boot port
EXPOSE 8080

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]