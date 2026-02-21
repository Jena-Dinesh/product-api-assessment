# Product API – Spring Boot Assessment

## Overview

This project is a RESTful Product Management API built using Spring Boot.
It supports full CRUD operations on Products, a One-to-Many relationship with Items, and secure access using JWT authentication with Refresh Token rotation.

The application follows a clean layered architecture and includes Docker support for containerized deployment.

---

# Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA (Hibernate)
* MySQL
* Spring Security (JWT + Refresh Token)
* Jakarta Validation
* Swagger / OpenAPI
* JUnit 5 & Mockito
* Docker & Docker Compose

---

# Project Architecture

Controller → Service → Repository → Database
↓
Security Layer
(JWT Filter + Spring Security Chain)

### Layer Description

* **Controller** – Handles REST endpoints
* **Service** – Contains business logic
* **Repository** – Data access layer using Spring Data JPA
* **Entity** – Maps Java objects to database tables
* **DTO** – Request/response transfer objects
* **Security** – JWT authentication & authorization filter chain

---

# Features Implemented

Full CRUD APIs for Products
One-to-Many Relationship (Product → Items)
JWT Authentication with Refresh Token Rotation
Role-Based Authorization (ADMIN / USER)
Input Validation using Jakarta Validation
Pagination Support for listing APIs
Global Exception Handling
Swagger API Documentation
Unit & Integration Testing (JUnit + Mockito)
Docker & Docker Compose Support

---

# Authentication Flow (JWT + Refresh Token)

1. User logs in via `/auth/login`
2. Server returns:

   * Access Token (short-lived)
   * Refresh Token (stored in DB)
3. Client uses Access Token in header:
   Authorization: Bearer <accessToken>
4. When access token expires → call `/auth/refresh`
5. New Access Token and rotated Refresh Token are generated

---

# Database Schema

## Product Table

| Column       | Type     |
| ------------ | -------- |
| id           | BIGINT   |
| product_name | VARCHAR  |
| created_by   | VARCHAR  |
| created_on   | DATETIME |
| modified_by  | VARCHAR  |
| modified_on  | DATETIME |

## Item Table

| Column     | Type        |
| ---------- | ----------- |
| id         | BIGINT      |
| product_id | BIGINT (FK) |
| quantity   | INT         |

## Users Table

| Column   | Type                     |
| -------- | ------------------------ |
| id       | BIGINT                   |
| username | VARCHAR (unique)         |
| password | VARCHAR (BCrypt encoded) |
| role     | VARCHAR                  |

## Refresh Token Table

| Column      | Type        |
| ----------- | ----------- |
| id          | BIGINT      |
| token       | VARCHAR     |
| expiry_date | DATETIME    |
| user_id     | BIGINT (FK) |

---

# API Endpoints

## Authentication APIs

| Method | Endpoint        | Description                              |
| ------ | --------------- | ---------------------------------------- |
| POST   | `/auth/login`   | Login & get access + refresh tokens      |
| POST   | `/auth/refresh` | Refresh access token using refresh token |

## Product APIs

| Method | Endpoint                      | Description                             |
| ------ | ----------------------------- | --------------------------------------- |
| GET    | `/api/v1/products`            | Get all products (pagination supported) |
| GET    | `/api/v1/products/{id}`       | Get product by ID                       |
| POST   | `/api/v1/products`            | Create product                          |
| PUT    | `/api/v1/products/{id}`       | Update product                          |
| DELETE | `/api/v1/products/{id}`       | Delete product                          |
| GET    | `/api/v1/products/{id}/items` | Get items of a product                  |

---

# Sample Postman Requests

## Login

POST /auth/login

```json
{
  "username": "admin",
  "password": "admin123"
}
```

## Create Product

POST /api/v1/products
Authorization: Bearer <token>

```json
{
  "productName": "Laptop",
  "createdBy": "admin"
}
```

---

Swagger UI:
http://localhost:8080/swagger-ui/index.html

---

# Docker Setup

## Dockerfile

```dockerfile
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

## docker-compose.yml

```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8
    container_name: mysql-db
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: assessment_db
    ports:
      - "3307:3306"

  app:
    build: .
    container_name: product-api
    ports:
      - "8080:8080"
    depends_on:
      - mysql
```

## Run with Docker

```bash
mvn clean package
docker compose up --build
```

---

# Testing

## Run Unit Tests

```bash
mvn test
```

### Test Coverage Includes

* Service Layer tests using Mockito
* Controller Layer tests using MockMvc
* Integration Tests using SpringBootTest + H2 in-memory database

---

# Security & Performance

* JWT authentication with refresh token rotation
* Role-based authorization
* Input validation using Jakarta Validation
* Database indexing on frequently queried columns
* CORS configuration enabled
* HTTPS-ready configuration supported

---

# Future Enhancements

* OAuth2 integration
* Redis caching for tokens
* Rate limiting
* Kubernetes deployment

---

# Author

Developed as part of a technical assessment demonstrating enterprise-grade Spring Boot REST API with JWT security, testing, and containerized deployment.
