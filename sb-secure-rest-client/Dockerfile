FROM openjdk:12-jdk-alpine
LABEL secure-rest-client="v1"
VOLUME /tmp
COPY target/sb-secure-rest-client-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080

