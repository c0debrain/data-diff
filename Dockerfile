FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} dates-calculator.jar
ENTRYPOINT ["java","-jar","/dates-calculator.jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"]