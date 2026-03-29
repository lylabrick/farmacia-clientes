# Farmacia Clientes

Pharmacy customer management system with reactive REST API built with Spring Boot WebFlux and MongoDB.

## Tech Stack

- **Java 21**
- **Spring Boot 3.3.5**
- **Spring WebFlux** (reactive REST API)
- **Spring Data MongoDB Reactive**
- **Lombok**
- **Maven**
- **SpringDoc OpenAPI 2.6.0** (Swagger UI)

## Prerequisites

- Java 21+
- MongoDB running on localhost:27017
- Maven 3.6+

## Running MongoDB

```bash
# Using Docker
docker run -d -p 27017:27017 --name mongodb mongo:latest

# Or install MongoDB locally and start the service
```

## Building the Project

```bash
mvn clean install
```

## Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation (Swagger/OpenAPI)

Once the application is running, you can access the interactive API documentation at:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

The Swagger UI provides:
- Interactive API documentation
- Try out endpoints directly from the browser
- View request/response schemas
- See all available endpoints with descriptions

## API Endpoints

### Customers

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |
| POST | `/api/customers` | Create a new customer |
| PUT | `/api/customers/{id}` | Update an existing customer |
| DELETE | `/api/customers/{id}` | Delete a customer |

## Example Requests

### Create Customer

```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "1234567890",
    "address": "123 Main St"
  }'
```

### Get All Customers

```bash
curl http://localhost:8080/api/customers
```

### Update Customer

```bash
curl -X PUT http://localhost:8080/api/customers/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane@example.com",
    "phone": "0987654321",
    "address": "456 Oak Ave"
  }'
```

### Delete Customer

```bash
curl -X DELETE http://localhost:8080/api/customers/{id}
```

## Project Structure

```
farmacia-clientes/
├── src/main/java/com/lylabrick/farmacia/
│   ├── FarmaciaClientesApplication.java
│   ├── config/
│   │   └── OpenApiConfig.java          # Swagger/OpenAPI configuration
│   ├── entity/
│   │   └── Customer.java
│   ├── repository/
│   │   └── CustomerRepository.java
│   ├── service/
│   │   ├── CustomerServiceInterface.java
│   │   └── impl/
│   │       └── CustomerServiceImpl.java
│   └── controller/
│       └── CustomerController.java
├── src/main/resources/
│   └── application.yml
└── pom.xml
```

## License

MIT License
