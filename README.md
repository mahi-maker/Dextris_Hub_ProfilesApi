# Job Seeker Profile API

A Spring Boot RESTful API for managing job seeker profiles, work experiences, education history, and resume uploads.

## Technologies Used

- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- H2 Database (for development)
- Lombok
- Spring Validation
- OpenAPI/Swagger Documentation

## Getting Started

### Prerequisites

- Java 17 or later
- Maven

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Maven:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

## API Documentation

Once the application is running, you can access the API documentation at:

```
http://localhost:8080/api/swagger-ui.html
```

## API Endpoints

### Profile Management

- `GET /api/profile?id={id}` - Get user profile
- `POST /api/profile` - Create new profile
- `PUT /api/profile?id={id}` - Update profile
- `POST /api/profile/resume?profileId={profileId}` - Upload resume

### Experience Management

- `GET /api/profile/experience?profileId={profileId}` - List experiences
- `GET /api/profile/experience/{id}?profileId={profileId}` - Get experience by ID
- `POST /api/profile/experience` - Add experience
- `PUT /api/profile/experience/{id}` - Update experience
- `DELETE /api/profile/experience/{id}?profileId={profileId}` - Remove experience

### Education Management

- `GET /api/profile/education?profileId={profileId}` - List education history
- `GET /api/profile/education/{id}?profileId={profileId}` - Get education by ID
- `POST /api/profile/education` - Add education
- `PUT /api/profile/education/{id}` - Update education
- `DELETE /api/profile/education/{id}?profileId={profileId}` - Remove education

## H2 Database Console

During development, you can access the H2 Database console at:

```
http://localhost:8080/api/h2-console
```

JDBC URL: `jdbc:h2:mem:jobseekerdb`  
Username: `sa`  
Password: `password`