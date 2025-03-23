# DefineX-Final-Case
# Advanced Task Management Application

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 📝 Description

The **Advanced Task Management Application** is a modern, in-house solution developed for **Lorem Ipsum Corporation** to replace their outdated legacy task management system. This application is designed to streamline project and task management, team member assignments, progress tracking, and priority management, all while ensuring robust file attachment support and state management.

## 🚀 Features

- **Project and Task Management**: Create, update, and manage projects and tasks with ease.
- **Team Member Assignment**: Assign team members to specific tasks.
- **Progress Tracking**: Track task progress with states like `Backlog`, `In Analysis`, `In Development/Progress`, `Cancelled`, `Blocked`, and `Completed`.
- **Priority Management**: Assign priorities to tasks (`Critical`, `High`, `Medium`, `Low`).
- **File Attachment Support**: Attach files to tasks for better collaboration.
- **Role-Based Access Control**: Secure endpoints with role-based access (`TEAM_MEMBERS`, `TEAM_LEADER`, `PROJECT_MANAGER`).

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **Hibernate**
- **Maven**
- **Lombok**
- **Swagger (for API documentation)**

## 📂 Project Structure
task-management/
├── src/
│ ├── main/
│ │ ├── java/com/example/taskmanagement/
│ │ │ ├── controller/ # REST Controllers
│ │ │ ├── dto/ # Data Transfer Objects
│ │ │ ├── entity/ # JPA Entities
│ │ │ ├── enums/ # Enumerations
│ │ │ ├── exception/ # Custom Exceptions
│ │ │ ├── mapper/ # Model Mappers
│ │ │ ├── repository/ # JPA Repositories
│ │ │ ├── security/ # Security Configuration
│ │ │ ├── service/ # Business Logic
│ │ │ └── TaskManagementApplication.java
│ │ └── resources/ # Configuration files
│ └── test/ # Unit and Integration Tests
└── pom.xml # Maven Build Configuration

Copy

## 🚀 Getting Started

### Prerequisites

- Java 21
- Maven 3.x
- PostgreSQL (or any other relational database)

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/task-management.git
   cd task-management
Configure the database:

Update the application.properties file with your database credentials.

Build the project:

bash
Copy
mvn clean install
Run the application:

bash
Copy
mvn spring-boot:run
Access the application:

The application will be running at http://localhost:8080.

API Documentation
Swagger UI: http://localhost:8080/swagger-ui.html

OpenAPI Docs: http://localhost:8080/v3/api-docs

📄 Endpoints
User Management
GET /api/v1/users - Get all users

GET /api/v1/users/{id} - Get user by ID

PUT /api/v1/users/{id} - Update user

DELETE /api/v1/users/{id} - Delete user

GET /api/v1/users/role/{role} - Get users by role

PUT /api/v1/users/update-role/{userId} - Update user role

Task Management
GET /api/v1/tasks - Get all tasks

GET /api/v1/tasks/{taskId} - Get task by ID

POST /api/v1/tasks/{projectId} - Create a new task

PUT /api/v1/tasks/{id} - Update task

DELETE /api/v1/tasks/{taskId} - Delete task

POST /api/v1/tasks/{taskId}/assign/{userId} - Assign task to user

PUT /api/v1/tasks/taskState/{taskId} - Update task state

GET /api/v1/tasks/{taskId}/comments - Get comments by task ID

GET /api/v1/tasks/{taskId}/attachments - Get attachments by task ID

Project Management
GET /api/v1/projects - Get all projects

GET /api/v1/projects/{projectId} - Get project by ID

POST /api/v1/projects - Create a new project

PUT /api/v1/projects/{projectId} - Update project

DELETE /api/v1/projects/{projectId} - Delete project

POST /api/v1/projects/{projectId}/members/{userId} - Add team member to project

GET /api/v1/projects/status/{status} - Get projects by status

GET /api/v1/projects/members/{projectId} - Get project members

Comment Management
POST /api/v1/comments/task/{taskId}/user/{userId} - Create a comment

PUT /api/v1/comments/{commentId} - Update comment

DELETE /api/v1/comments/{commentId} - Delete comment

Attachment Management
POST /api/v1/attachments/task/{taskId} - Upload attachment

PUT /api/v1/attachments/{attachmentId} - Update attachment

DELETE /api/v1/attachments/{attachmentId} - Delete attachment

Authentication
POST /api/v1/auth/register - Register a new user

POST /api/v1/auth/authenticate - Authenticate user

🧪 Testing
Unit tests are written using JUnit 5 and Mockito.

To run tests:
mvn test
