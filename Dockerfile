FROM amazoncorretto:24

WORKDIR /app

COPY ./src/main/resources/haarcascades/haarcascade_frontalface_alt.xml haarcascade_frontalface_alt.xml
ENV CASCADE_CLASSIFIER_PATH="/app/haarcascade_frontalface_alt.xml"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 9998

ENTRYPOINT ["java", "-jar", "app.jar", "listen"]
