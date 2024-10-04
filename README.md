# Spring Boot User-Product Management API

## 🚀 Overview
A robust REST API built with Spring Boot and MongoDB that implements user authentication and product management functionalities. This project demonstrates best practices in building secure and scalable Spring Boot applications with token-based authentication, role-based authorization, and comprehensive CRUD operations.

## 🔑 Key Features
- **User Authentication & Authorization**
    - JWT-based authentication
    - Role-based access control (Admin/User roles)
    - Secure password hashing with BCrypt

- **Product Management**
    - Complete CRUD operations for products
    - Public product listing endpoint
    - User-specific product management
    - Input validation and sanitization

- **Security Implementations**
    - MongoDB injection prevention
    - Cross-Origin Resource Sharing (CORS) configuration
    - Rate limiting for API endpoints
    - Secure password storage
    - HTTPS support

## 🛠️ Technologies
- **Framework**: Spring Boot 3.3.4
- **Security**: Spring Security
- **Database**: MongoDB
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tool**: Maven
- **Java Version**: 17

## 📋 API Endpoints
### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Authenticate user

### Products
- `GET /api/products` - List all products (Public)
- `POST /api/products` - Create new product (Authenticated)
- `GET /api/products/{id}` - Get product details (Authenticated)
- `PUT /api/products/{id}` - Update product (Authenticated)
- `DELETE /api/products/{id}` - Delete product (Authenticated)

### Users
- `GET /api/users` - List all users (Admin only)
- `GET /api/users/{id}` - Get user details (Authenticated)
- `PUT /api/users/{id}` - Update user (Authenticated)
- `DELETE /api/users/{id}` - Delete user (Admin only)

## 🚀 Getting Started
1. Clone the repository
    ```bash
   git clone https://github.com/OumarLAM/lets-play.git
    ```
2. Configure MongoDB connection in `application.properties`
3. Run using `./mvnw spring-boot:run`
4. Access API at `http://localhost:8080/api`

## 📝 Prerequisites
- Java 17 or higher
- MongoDB
- Maven

## 🔒 Security Features
- Password encryption using BCrypt
- JWT token-based authentication
- Role-based access control
- Input validation and sanitization
- Protection against MongoDB injection
- Rate limiting implementation

## 📖 Documentation
Detailed API documentation is available at `/swagger-ui.html` when running the application.

## 🤝 Contributing
Contributions, issues, and feature requests are welcome! Feel free to check [issues page](#).

## 👨‍💻 Author
**Oumar LAM**
- GitHub: [@OumarLAM](https://github.com/OumarLAM)
- Twitter: [@oumarlam_fcb](https://twitter.com/oumarlam_fcb)
- Email: [oumarlam154@gmail.com](mailto:oumarlam154@gmail.com)

## 📜 License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---
⭐️ If you find this project helpful, please give it a star!