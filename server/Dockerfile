FROM adoptopenjdk/openjdk11
VOLUME /tmp
RUN mkdir /app
EXPOSE 8080
WORKDIR /app
ADD build/libs /app/build/libs
ENV JAVA_OPTS=""
ENV JAR_TARGET "tsk2-server-0.0.1-SNAPSHOT.jar"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar build/libs/tsk2-server-0.0.1-SNAPSHOT.jar --server.port=80" ]