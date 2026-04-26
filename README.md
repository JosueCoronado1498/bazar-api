# 🛒 Bazar API - Backend con Spring Boot

API REST para la gestión de ventas, productos y clientes.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Swagger (OpenAPI)
- Maven

---

## 📌 Funcionalidades

- CRUD de productos
- CRUD de clientes
- Registro de ventas
- Control automático de stock
- Cálculo de total por venta
- Consulta de ventas por fecha
- Obtención de la venta con mayor monto

---

## 📂 Estructura del proyecto

- controller → Endpoints REST
- service → Lógica de negocio
- repository → Acceso a datos (JPA)
- model → Entidades
- dto → Objetos de transferencia de datos
- security → Manejo de login

---

## ⚙️ Configuración del proyecto

### 1. Clonar repositorio

git clone https://github.com/JosueCoronado1498/bazar-api  
cd bazar

---

### 2. Configurar propiedades

Copiar el archivo de ejemplo:

src/main/resources/application-example.properties

y renombrarlo a:

application.properties

Luego completar con tus datos:

spring.datasource.url=jdbc:mysql://localhost:3306/bazar_db  
spring.datasource.username=TU_USUARIO  
spring.datasource.password=TU_PASSWORD


---

## 📖 Documentación API (Swagger)

http://localhost:8080/swagger-ui.html

---

## 🔐 Seguridad

El proyecto utiliza Spring Security con autenticación básica.

---

## 📊 Endpoints principales

### Ventas
- GET /ventas
- GET /ventas/{id}
- POST /ventas
- PUT /ventas/{id}
- DELETE /ventas/{id}

### Consultas
- GET /ventas/productos/{id}
- GET /ventas/fecha/{fecha}
- GET /ventas/mayor

---

## 🧠 Autor

Josué Coronado

---

## 📌 Notas

- Proyecto de práctica backend con Java y Spring Boot
- Arquitectura en capas + DTO + buenas prácticas