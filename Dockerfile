FROM eclipse-temurin:21

WORKDIR /app

COPY build/libs/InventoryManagementSystem-0.0.1-SNAPSHOT.jar /app/InventoryManagementSystem-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "InventoryManagementSystem-0.0.1-SNAPSHOT.jar"]
