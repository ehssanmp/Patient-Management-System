FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY common-schema/pom.xml common-schema/pom.xml
COPY common-schema/src common-schema/src

RUN mvn -f common-schema/pom.xml clean install

# --- Step 2: Build the Billing Service ---
COPY patient-service/pom.xml patient-service/pom.xml
COPY patient-service/src patient-service/src

RUN mvn -f patient-service/pom.xml clean package

FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/patient-service/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]