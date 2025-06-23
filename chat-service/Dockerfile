FROM amazoncorretto:21 as builder
WORKDIR /app
COPY . .
RUN ./gradlew :chat-service:clean :chat-service:build -x test

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/chat-service/build/libs/chat-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]