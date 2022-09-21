FROM java:8
VOLUME /tmp
ADD serverprovider-1.0-SNAPSHOT.jar serverprovider.jar
EXPOSE 8001
ENTRYPOINT [ "java", "-jar", "/serverprovider.jar" ]