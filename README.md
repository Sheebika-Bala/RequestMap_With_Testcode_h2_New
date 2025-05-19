# RequestMap_With_Testcode_h2_New
# Employee Management API

A Spring Boot RESTful API for managing employees, supporting CRUD operations and custom queries.

## Features

- Create, read, update, patch, and delete employees
- Query employees by email domain
- Input validation with meaningful error messages
- Exception handling for not found resources

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (for testing)
- Maven

## Getting Started

### Prerequisites

- Java 17+
- Maven

### Setup

1. Clone the repository:
   git clone https://github.com/Sheebika-Bala/RequestMap_With_Testcode_H2.git cd RequestMap_With_Testcode_H2
2. Build the project:
   mvn clean install
3. Run the application:
   mvn spring-boot:run

The API will be available at `http://localhost:8080`.

## API Endpoints

- `GET /employees` - List all employees
- `GET /employees/{id}` - Get employee by ID
- `GET /employees/domain` - List employees with email domain `@dcis.net`
- `POST /employees` - Create a new employee
- `PUT /employees/{id}` - Update an employee
- `PATCH /employees/{id}` - Partially update an employee
- `DELETE /employees/{id}` - Delete an employee

