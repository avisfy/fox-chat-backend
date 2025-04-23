FROM amazoncorretto:21 as builder
WORKDIR /app
COPY . .
RUN ./gradlew :user-service:clean :user-service:build -x test

FROM amazoncorretto:21
WORKDIR /app
COPY --from=builder /app/user-service/build/libs/user-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]