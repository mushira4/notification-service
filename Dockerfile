FROM eclipse-temurin:21.0.3_9-jre-alpine
LABEL maintainer="mushira4"

ARG JAR_FILE=build/libs/notification-service-rest-1.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} notification-service.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/notification-service.jar"]
