# ===== STAGE 1: Build =====
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# ===== STAGE 2: Run =====
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia exatamente o JAR gerado:
COPY --from=builder /app/target/ponte-ia.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
