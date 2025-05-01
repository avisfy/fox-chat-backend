FROM amazoncorretto:21 as builder
WORKDIR /app
COPY . .
RUN ./gradlew :notification-service:clean :notification-service:build -x test

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/notification-service/build/libs/notification-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]