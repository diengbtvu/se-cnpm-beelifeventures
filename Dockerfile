# Giai đoạn 1: Xây dựng ứng dụng
FROM maven:3.8-eclipse-temurin-17 AS builder
WORKDIR /build
# Copy pom.xml trước để tận dụng cache tốt hơn
COPY pom.xml .
# Tải các dependency (sẽ được lưu vào cache nếu pom.xml không thay đổi)
RUN mvn dependency:go-offline -B
# Copy mã nguồn
COPY src/ ./src/
# Xây dựng ứng dụng
RUN mvn package -DskipTests

# Giai đoạn 2: Tạo image runtime
FROM openjdk:11-jre-slim
WORKDIR /app
# Copy file JAR từ giai đoạn build
COPY --from=builder /build/target/*.jar app.jar
# Install curl for health checks
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Mở cổng mà ứng dụng sẽ chạy
EXPOSE 8080
# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
