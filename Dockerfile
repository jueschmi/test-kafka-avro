FROM openjdk:8-jre
EXPOSE 8080 8081

COPY build/install/test-kafka-avro /test-kafka-avro/
COPY config.yml  /test-kafka-avro/config.yml

# mount keys directory
RUN mkdir -p /application/keys
VOLUME /application/keys

CMD test-kafka-avro/bin/test-kafka-avro server /test-kafka-avro/config.yml
