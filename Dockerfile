# Use AdoptOpenJDK as the base image
# FROM adoptopenjdk:21-jdk-hotspot
FROM openjdk:21-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the Quarkus native executable to the working directory
COPY target/*-runner /app/application

# Expose the application port
EXPOSE 8080

# Set the default command to run your Quarkus application
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
