# API Testing Quick Guide

Use Postman or Thunder Client.

## 1. Health
GET http://localhost:8080/api/health

## 2. Register
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "name": "Admin User",
  "email": "admin@example.com",
  "password": "123456"
}

## 3. Login
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "admin@example.com",
  "password": "123456"
}

Save the token.

For protected requests:
Authorization: Bearer <token>

## 4. Create donor
POST http://localhost:8080/api/donors

{
  "name": "Amit",
  "email": "amit@example.com",
  "phone": "9876543210",
  "bloodGroup": "O+",
  "age": 21,
  "gender": "Male",
  "city": "Mumbai",
  "eligible": true
}

## 5. Add inventory
POST http://localhost:8080/api/inventory

{
  "bloodGroup": "O+",
  "units": 10,
  "storageLocation": "Rack A1"
}

## 6. Create blood request
POST http://localhost:8080/api/requests

{
  "patientName": "Patient One",
  "bloodGroup": "O+",
  "unitsRequired": 2,
  "hospitalName": "City Hospital",
  "contactNumber": "9999999999",
  "urgency": "HIGH"
}

## 7. Fulfill request
PATCH http://localhost:8080/api/requests/1/status?status=FULFILLED

## 8. Record donation
POST http://localhost:8080/api/donations

{
  "donor": {
    "id": 1
  },
  "units": 1,
  "notes": "Regular donation"
}

## 9. Dashboard
GET http://localhost:8080/api/admin/dashboard
