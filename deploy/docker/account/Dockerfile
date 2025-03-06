FROM eclipse-temurin:21
ENV PROJECT_DIR=/app
RUN mkdir $PROJECT_DIR
RUN cd $PROJECT_DIR
COPY next-gen-inbound.jar $PROJECT_DIR/app.jar
COPY application.properties /app/config/application.properties

WORKDIR $PROJECT_DIR
EXPOSE 80
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar app.jar" ]
