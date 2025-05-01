FROM amazoncorretto:21 as builder
WORKDIR /app
COPY . .
RUN ./gradlew :message-service:clean :message-service:build -x test

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/message-service/build/libs/message-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]