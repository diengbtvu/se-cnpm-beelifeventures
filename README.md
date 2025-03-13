# Spring Boot Application - Docker Guide

Hướng dẫn chạy ứng dụng Spring Boot bằng Docker với tự động build.

## Yêu cầu

- Docker đã được cài đặt
- Docker Compose đã được cài đặt

## Hướng dẫn chạy

### 1. Build và chạy ứng dụng tự động

Chỉ cần chạy một lệnh duy nhất:

```bash
docker-compose up --build
```

Hoặc chạy ở chế độ detached (chạy ngầm):

```bash
docker-compose up --build -d
```

Docker sẽ tự động:
- Build source code bằng Maven
- Đóng gói ứng dụng thành file JAR
- Tạo image Docker với ứng dụng
- Chạy container từ image vừa tạo

### 2. Kiểm tra ứng dụng

Truy cập ứng dụng tại địa chỉ:
- http://localhost:8080

### 3. Dừng ứng dụng

```bash
docker-compose down
```

## Các lệnh Docker hữu ích

- Xem logs:
```bash
docker-compose logs
```

- Xem logs theo thời gian thực:
```bash
docker-compose logs -f
```

- Khởi động lại ứng dụng:
```bash
docker-compose restart
```

- Kiểm tra trạng thái:
```bash
docker-compose ps
```

## Cấu hình

Bạn có thể điều chỉnh các cấu hình trong:
- `Dockerfile`: Thay đổi cấu hình build và runtime
- `docker-compose.yml`: Thay đổi port, biến môi trường

## Lưu ý

- Nếu bạn đang sử dụng Gradle thay vì Maven, bạn cần sửa lại Dockerfile sử dụng image Gradle
- Không cần build ứng dụng trước khi chạy Docker, vì quá trình build sẽ tự động diễn ra trong container

## Xử lý lỗi thường gặp

- **Port conflict**: Nếu cổng 8080 đã được sử dụng, thay đổi mapping trong `docker-compose.yml`:
  ```yaml
  ports:
    - "8081:8080"  # Thay 8081 bằng cổng khác
  ```

- **Out of memory**: Tăng bộ nhớ cho container trong `docker-compose.yml`:
  ```yaml
  services:
    app:
      deploy:
        resources:
          limits:
            memory: 1G
