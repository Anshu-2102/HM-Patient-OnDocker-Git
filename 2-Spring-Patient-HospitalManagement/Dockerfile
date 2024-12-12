FROM openjdk:17-jdk-alpine
MAINTAINER "Anshu"
EXPOSE 8082
COPY ./target/patientService.jar   patientService.jar 
CMD ["java","-jar","patientService.jar"]

#CMD ["java","-Dspring.profiles.active="development", "-jar","product.war"]
#WORKDIR  /usr/local/bin/