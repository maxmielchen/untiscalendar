FROM maven:3.9-amazoncorretto-17 AS builder
COPY /app /app
WORKDIR /app
RUN mvn clean package

FROM amazoncorretto:17-alpine
COPY --from=builder /app/target/app-jar-with-dependencies.jar /
EXPOSE 4567
CMD java -jar app-jar-with-dependencies.jar

