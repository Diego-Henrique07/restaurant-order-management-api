# 🚀 Restaurant Order Management API

A robust REST API built with Spring Boot to manage customers, products, and orders with a complete order lifecycle and real database persistence using PostgreSQL.

---

## 🔥 Highlights

- Full CRUD for Customers and Products
- Order creation with multiple items
- Order status workflow:
  - CREATED → PREPARING → READY → DELIVERED
- Automatic total price calculation
- Relational database modeling with JPA/Hibernate
- PostgreSQL integration (real-world database)

---

## 🧠 Business Logic

This system simulates a real restaurant backend, including:

- Order lifecycle management
- Data consistency between entities
- Relationship handling (Customer ↔ Order ↔ OrderItem ↔ Product)
- Controlled state transitions for orders

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

---

## 📦 API Endpoints

### 👤 Customers
- `POST /customers`
- `GET /customers`
- `GET /customers/{id}`
- `DELETE /customers/{id}`

### 🍔 Products
- `POST /products`
- `GET /products`
- `GET /products/{id}`
- `DELETE /products/{id}`

### 📋 Orders
- `POST /orders`
- `POST /orders/{id}/items`
- `PATCH /orders/{id}/status`
- `GET /orders`
- `GET /orders/{id}`

---
