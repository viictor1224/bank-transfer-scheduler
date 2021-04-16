FROM maven:3.6.3-openjdk-16-slim as MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM openjdk:16-jdk

COPY --from=MAVEN_BUILD /target/bank-transfer-scheduler-0.0.1-SNAPSHOT.jar /transfers-scheduler.jar

CMD [ "java", "-jar", "/transfers-scheduler.jar" ]