FROM gradle:7.3.2

WORKDIR /server

COPY settings.gradle .
COPY build.gradle .
COPY src/ ./src

# RUN gradle bootJar
CMD ["gradle", "bootRun"]

# FROM openjdk:11

# CMD ["java -jar /server/build/libs/community-0.0.1-SNAPSHOT.jar"]
