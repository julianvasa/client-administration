FROM openjdk:8
ADD target/client-administration-1.0.jar client-administration-1.0.jar
ENTRYPOINT ["java","-jar","client-administration-1.0.jar"]
