FROM openjdk:12-jdk-alpine
LABEL secure-rest="v1"
VOLUME /tmp
COPY target/sb-secure-rest-server-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8443