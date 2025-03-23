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

## 📄 Endpoints

### User Management
![user-controller](https://github.com/user-attachments/assets/6a963cd5-faf0-4155-9e01-383fcd463206)

- **GET** `/api/v1/users`  
  Get all users.

- **GET** `/api/v1/users/{id}`  
  Get user by ID.

- **PUT** `/api/v1/users/{id}`  
  Update user.

- **DELETE** `/api/v1/users/{id}`  
  Delete user.

- **GET** `/api/v1/users/role/{role}`  
  Get users by role.

- **PUT** `/api/v1/users/update-role/{userId}`  
  Update user role.

---

### Task Management
![task controller](https://github.com/user-attachments/assets/5759257b-e9ad-4cf8-8a53-247580e93e67)

- **GET** `/api/v1/tasks`  
  Get all tasks.

- **GET** `/api/v1/tasks/{taskId}`  
  Get task by ID.

- **POST** `/api/v1/tasks/{projectId}`  
  Create a new task.

- **PUT** `/api/v1/tasks/{id}`  
  Update task.

- **DELETE** `/api/v1/tasks/{taskId}`  
  Delete task.

- **POST** `/api/v1/tasks/{taskId}/assign/{userId}`  
  Assign task to user.

- **PUT** `/api/v1/tasks/taskState/{taskId}`  
  Update task state.

- **GET** `/api/v1/tasks/{taskId}/comments`  
  Get comments by task ID.

- **GET** `/api/v1/tasks/{taskId}/attachments`  
  Get attachments by task ID.

---

### Project Management
![project controller](https://github.com/user-attachments/assets/dbaaef17-8937-4829-b7c9-0c6b21e5963a)

- **GET** `/api/v1/projects`  
  Get all projects.

- **GET** `/api/v1/projects/{projectId}`  
  Get project by ID.

- **POST** `/api/v1/projects`  
  Create a new project.

- **PUT** `/api/v1/projects/{projectId}`  
  Update project.

- **DELETE** `/api/v1/projects/{projectId}`  
  Delete project.

- **POST** `/api/v1/projects/{projectId}/members/{userId}`  
  Add team member to project.

- **GET** `/api/v1/projects/status/{status}`  
  Get projects by status.

- **GET** `/api/v1/projects/members/{projectId}`  
  Get project members.

---

### Comment Management
![comments controller](https://github.com/user-attachments/assets/8875ed01-8d0c-4dd9-8988-4255599c7882)

- **POST** `/api/v1/comments/task/{taskId}/user/{userId}`  
  Create a comment.

- **PUT** `/api/v1/comments/{commentId}`  
  Update comment.

- **DELETE** `/api/v1/comments/{commentId}`  
  Delete comment.

---

### Attachment Management
![attachments controler](https://github.com/user-attachments/assets/db709bbf-c8cd-48fa-864a-b2b39a55b341)

- **POST** `/api/v1/attachments/task/{taskId}`  
  Upload attachment.

- **PUT** `/api/v1/attachments/{attachmentId}`  
  Update attachment.

- **DELETE** `/api/v1/attachments/{attachmentId}`  
  Delete attachment.

---

### Authentication
![authentication sontroller](https://github.com/user-attachments/assets/7afcd440-fa4e-4e1e-b311-875bd61d78bd)

- **POST** `/api/v1/auth/register`  
  Register a new user.

- **POST** `/api/v1/auth/authenticate`  
  Authenticate user.

### Installation

1. **Clone the repository**:
   ```bash
   git clone [https://github.com/muhammet-enes-aksoy/DefineX-Final-Case]

