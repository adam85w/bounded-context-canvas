FROM docker.io/openjdk:17
WORKDIR /app
COPY ./target/bounded-context-canvas-*.jar bounded-context-canvas.jar
CMD java -jar bounded-context-canvas.jar
EXPOSE 8083