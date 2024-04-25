FROM docker.io/openjdk:17.0.2
WORKDIR /app
COPY ./target/bounded-context-canvas-*.jar bounded-context-canvas.jar
CMD java -jar bounded-context-canvas.jar
EXPOSE 8083