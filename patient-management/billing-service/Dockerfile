FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-schema/pom.xml common-schema/pom.xml
COPY common-schema/src common-schema/src

# Install common-schema locally inside the container
RUN mvn -f common-schema/pom.xml clean install

# --- Step 2: Build the Billing Service ---
COPY billing-service/pom.xml billing-service/pom.xml
COPY billing-service/src billing-service/src

RUN mvn -f billing-service/pom.xml clean package

FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/billing-service/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4001

EXPOSE 9001

ENTRYPOINT ["java", "-jar", "app.jar"]