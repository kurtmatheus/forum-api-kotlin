FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD /target/forumapi-0.0.1-SNAPSHOT.jar forumapi.jar
ENTRYPOINT ["java", "-jar", "forumapi.jar"]