FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker"]