# Spring Boot CRUD with Thymeleaf

A simple CRUD (Create, Read, Update, Delete) application built with Spring Boot and Thymeleaf for managing fruit (buah) data.

## Technologies Used

- **Java 21**
- **Spring Boot 3.x**
- **Apache Maven 3.9.8**
- **Thymeleaf**
- **Lombok**
- **PostgreSQL**

## Features
- List all fruits with formatted price display (Rp)
- Add new fruit entries
- Edit existing fruit data
- Soft delete fruit entries
- Responsive UI using Bootstrap

## Project Structure

### Controllers

1. **BuahController** (`/api/buah`):
    - Handles REST API endpoints for CRUD operations.
    - Example endpoints:
        - `POST /api/buah/create`: Create a new fruit.
        - `PUT /api/buah/edit/{id}`: Update fruit details.
        - `DELETE /api/buah/delete/{id}`: Delete a fruit.

2. **BuahWebController** (`/buah`):
    - Handles web page requests for managing fruits.
    - Example views:
        - `/buah`: Displays a list of fruits.
        - `/buah/create`: Form for adding a new fruit.
        - `/buah/edit/{id}`: Form for editing an existing fruit.

### Services

- **BuahService**:
    - Contains the business logic for managing "Buah" entities.

### DTOs (Data Transfer Objects)

- **BuahCreateRequestDTO**: Used for creating a new fruit.
- **BuahUpdateRequestDTO**: Used for updating an existing fruit.
- **BuahResponseDTO**: Used for returning fruit data to clients.

### Views

- **Thymeleaf templates** located in `src/main/resources/templates/buah`:
    - `list.html`: Displays the list of fruits.
    - `form.html`: Form for adding or editing fruit details.

## Running the Application

1. Clone the repository:
   ```bash
   git@github.com:dwididit/springboot-crud-thymeleaf.git
   cd springboot-crud-thymeleaf/
2. Build the project
    ```bash
    mvn clean install
    ```
3. Run the Application
    ```bash
    mvn spring-boot:run
    ```
4. Access the application:
    ```bash
    Web interface: http://localhost:9090/buah
    Swagger UI: http://localhost:9090/swagger-ui/index.html
    ```