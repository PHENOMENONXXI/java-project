FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
ARG JAR_FILE=target/*.jar
COPY . /path/to/dir/in/image
ADD ${JAR_FILE} /opt/hello.jar

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/hello.jar
