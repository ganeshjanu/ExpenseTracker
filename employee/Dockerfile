# For Java 8, try this
FROM openjdk:8-jdk-alpine



# Refer to Maven build -> finalName
#ARG JAR_FILE=/var/jenkins_home/workspace/test6-pipeline/employee/target/employee-0.0.1-SNAPSHOT.jar

# cd /opt/app
#WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/employee.jar
COPY target/employee-0.0.1-SNAPSHOT.jar employee.jar

# java -jar /opt/app/employee.jar
ENTRYPOINT ["java","-jar","employee.jar"]
