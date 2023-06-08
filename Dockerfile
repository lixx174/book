FROM eclipse-temurin:17-jre-alpine

ENV TZ "Asia/Shanghai"
ENV PARAMS "--spring.profiles.active=prod"
ENV JAVA_OPS "-XX:+UseG1GC -Xms512m -Xmx512m"

ADD build/libs/*.jar /app.jar
ENTRYPOINT ["sh","-c","java -server -Dfile.encoding=UTF-8 -Duser.timezone=$TZ -Djava.security.egd=file:/dev/./urandom -XX:+HeapDumpOnOutOfMemoryError $JAVA_OPS -jar /app.jar $PARAMS"]
