# Smart Library Management System

A modern library management system built with Spring Boot, providing efficient book tracking, user management, and loan processing capabilities.

## Features

- 📚 Book Management
  - Add, update, and remove books
  - Track book status (Available, Borrowed, Reserved)
  - Search books by title, author, ISBN, or genre
  - Manage book inventory

- 👥 User Management
  - User registration and profile management
  - Track user membership status
  - View user loan history
  - Manage user accounts

- 📖 Loan Management
  - Process book loans and returns
  - Track due dates
  - Calculate fines for overdue books
  - Handle book reservations

## Technology Stack

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5
- Lombok
- Swagger/OpenAPI

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/burakatakl/smart-library.git
   ```

2. Navigate to the project directory:
   ```bash
   cd smart-library
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── library/
│   │           ├── config/        # Configuration classes
│   │           ├── model/         # Entity classes
│   │           ├── repository/    # Data access layer
│   │           ├── service/       # Business logic layer
│   │           └── controller/    # REST controllers
│   └── resources/
│       └── application.properties # Application configuration
└── test/
    └── java/
        └── com/
            └── library/
                └── service/       # Unit tests
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Burak Ataklı - [@burakatakl](https://github.com/burakatakl)

Project Link: [https://github.com/burakatakl/smart-library](https://github.com/burakatakl/smart-library)
