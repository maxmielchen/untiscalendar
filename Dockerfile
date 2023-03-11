FROM maven:3.9-amazoncorretto-17 AS builder
COPY /app /app
WORKDIR /app
RUN mvn clean package

FROM amazoncorretto:17-alpine
ENV ROOM="Room"
ENV TEACHER="Teacher"
ENV SUMMARY="School"
ENV USERNAME=""
ENV PASSWORD=""
ENV SERVER="niobe.webuntis.com"
ENV SCHOOL=""
ENV SSL="true"
ENV TOKEN="secret"

COPY --from=builder /app/target/app-jar-with-dependencies.jar /
EXPOSE 8080
CMD java -jar app-jar-with-dependencies.jar $ROOM $TEACHER $SUMMARY $USERNAME $USERNAME $PASSWORD $SERVER $SCHOOL $SSL $TOKEN

