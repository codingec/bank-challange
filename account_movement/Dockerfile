FROM gradle:8.4-jdk21 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

FROM eclipse-temurin:21
ENV PROJECT_DIR=/app
RUN mkdir $PROJECT_DIR
RUN cd $PROJECT_DIR
COPY --from=build /app/build/libs/account_movement-0.0.1-SNAPSHOT.jar $PROJECT_DIR/app.jar

WORKDIR $PROJECT_DIR
EXPOSE 83
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar app.jar" ]
