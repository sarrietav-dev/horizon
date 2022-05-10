FROM openjdk:11-jdk AS runner
WORKDIR /app
COPY target/ ./

EXPOSE 8080

CMD ["java", "-jar", "Horizon-0.0.1-SNAPSHOT.jar"]
