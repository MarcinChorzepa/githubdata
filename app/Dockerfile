FROM openjdk:8-jdk-alpine
LABEL Marcin Chorzepa <marcin.r.chorzepa@gmail.com>
COPY target/app-*.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080