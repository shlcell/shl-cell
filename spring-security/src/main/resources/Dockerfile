FROM java:8
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar
EXPOSE 10086
ENTRYPOINT ["java","-jar","/app.jar"]