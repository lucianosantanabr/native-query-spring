FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/api-native-query.jar /app/api-native-query.jar

ENTRYPOINT ["java", "-jar", "api-native-query.jar"]