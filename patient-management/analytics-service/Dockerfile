FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-schema/pom.xml common-schema/pom.xml
COPY common-schema/src common-schema/src

# Install common-schema locally inside the container
RUN mvn -f common-schema/pom.xml clean install

# --- Step 2: Build the Billing Service ---
COPY analytics-service/pom.xml analytics-service/pom.xml
COPY analytics-service/src analytics-service/src

RUN mvn -f analytics-service/pom.xml clean package

FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/analytics-service/target/analytics-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4002

ENTRYPOINT ["java", "-jar", "app.jar"]