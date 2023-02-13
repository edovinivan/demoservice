FROM openjdk:11
VOLUME /tmp
COPY target/demoservice-0.0.1-SNAPSHOT.jar resource.jar
COPY src/main/resources/keycloak.json keycloak.json
RUN bash -c "touch /resource.jar"
EXPOSE 8020
ENTRYPOINT ["java","-jar","resource.jar"]

# docker run -d -p 18086:8020 --name docker-resource demoservice/resource:1.0