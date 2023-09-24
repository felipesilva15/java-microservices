FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=04-book-service/target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]