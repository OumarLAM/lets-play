# Use official OpenJDK image as the base image
FROM eclipse-temurin:17-jdk-focal

# Set working directory
WORKDIR /app

# Copy the Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Copy the project files
COPY pom.xml .
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Expose the port the app runs on
EXPOSE 8443

# Run the application
CMD ["java", "-jar", "target/lets-play-0.0.1-SNAPSHOT.jar"]