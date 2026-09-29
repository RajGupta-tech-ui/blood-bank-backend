# Blood Bank Management System - Backend

Backend for the FSJP mini project.

## Tech stack
- Java 17
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- Spring Security
- JWT
- Lombok

## Modules
1. Authentication
2. Donor management
3. Blood inventory
4. Blood requests
5. Donations
6. Admin dashboard

## Main API endpoints

### Public
- POST `/api/auth/register`
- POST `/api/auth/login`
- GET `/api/health`

### Authenticated
- GET/POST/PUT/DELETE `/api/donors`
- GET `/api/donors/blood-group/{group}`
- GET `/api/donors/eligible`
- GET/POST/PATCH `/api/inventory`
- GET/POST/PATCH `/api/requests`
- GET/POST `/api/donations`
- GET `/api/admin/dashboard`

## Run in VS Code

### 1. Requirements
Install:
- JDK 17
- Maven 3.9+
- MySQL 8
- VS Code
- Extension Pack for Java

Verify:
```bash
java -version
mvn -version
```

### 2. Database
Create the database:
```sql
CREATE DATABASE blood_bank;
```

Edit:
`src/main/resources/application.properties`

Set:
```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

The backend uses:
```properties
spring.jpa.hibernate.ddl-auto=update
```
so JPA can create/update the mapped tables during development.

### 3. Run
Open the project folder in VS Code terminal:
```bash
mvn clean install
mvn spring-boot:run
```

Backend:
`http://localhost:8080`

Health check:
`GET http://localhost:8080/api/health`

## Authentication

Register:
```http
POST /api/auth/register
Content-Type: application/json

{
  "name": "Raj",
  "email": "raj@example.com",
  "password": "123456"
}
```

Login:
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "raj@example.com",
  "password": "123456"
}
```

Copy the returned JWT token and send it with protected requests:
```http
Authorization: Bearer YOUR_TOKEN
```

## Make a user an ADMIN for the college demo

First register normally through `/api/auth/register`. Then, in MySQL:

```sql
USE blood_bank;
UPDATE users SET role = 'ADMIN' WHERE email = 'admin@example.com';
```

Log in again after changing the role so the new JWT contains `ADMIN`.

The `/api/admin/**` endpoints require an ADMIN token.

## GitHub

```bash
git init
git add .
git commit -m "Initial Blood Bank backend"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/blood-bank-backend.git
git push -u origin main
```

Never commit real database passwords or production JWT secrets.
