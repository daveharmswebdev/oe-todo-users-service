FROM amazoncorretto:11.0.2
ADD target/users-service-0.0.1-SNAPSHOT.jar users-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "users-service-0.0.1-SNAPSHOT.jar"]