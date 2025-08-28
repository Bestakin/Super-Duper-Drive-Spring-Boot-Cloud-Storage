# SuperDuperDrive (Spring Boot Cloud Storage)
A Spring Boot web application that provides secure cloud storage features, including file management, note-taking, and credential storage with encryption.

--- 

## Tech Stack
- Java, Spring Boot
- Spring Security (authentication & authorization)
- Thymeleaf (UI templates)
- MyBatis (database persistence)
- H2 in-memory DB
- Selenium (end-to-end testing)

---

## Features
- **User authentication** with Spring Security + hashed passwords
- **File management**: upload, download, delete files
- **Notes management**: create, edit, delete notes
- **Credential management**: store, encrypt, and decrypt website credentials
- **Tests**: JUnit + Selenium for signup, login, and CRUD features

---

## Quick Start
./mvnw spring-boot:run

Visit: http://localhost:9999

---

## Default pages:

- /signup → Create account
- /login → Login
- /home → Files, Notes, Credentials

---

## Demo
Below is a walkthrough of the app features:
- **Signup & Login**: Secure user authentication
- **Files tab**: Upload, view, and delete files
- **Notes tab**: Create, update, delete notes
- **Credentials tab**: Store & encrypt website credentials

---

## Screenshots

---

## Testing
- Unit tests with JUnit & Mockito
- Selenium tests for:
 - Unauthorized access restrictions
 - User signup + login + logout flow
 - Notes: create, update, delete
 - Credentials: store, encrypt/decrypt, delete

---

## Project Structure
- `controller/` → Spring MVC controllers
- `service/` → Services like HashService, EncryptionService
- `mapper/` → MyBatis mappers for DB access
- `model/` → POJOs for Files, Notes, Credentials, Users
- `resources/templates/` → Thymeleaf HTML pages
- `resources/static/` → CSS, JS


