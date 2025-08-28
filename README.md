# SuperDuperDrive (Spring Boot Cloud Storage)
A Spring Boot web application that provides secure cloud storage features, including file management, note-taking, and credential storage with encryption.

## Tech Stack
- Java, Spring Boot
- Spring Security (authentication & authorization)
- Thymeleaf (UI templates)
- MyBatis (database persistence)
- H2 in-memory DB
- Selenium (end-to-end testing)

## Features
- **User authentication** with Spring Security + hashed passwords
- **File management**: upload, download, delete files
- **Notes management**: create, edit, delete notes
- **Credential management**: store, encrypt, and decrypt website credentials
- **Tests**: JUnit + Selenium for signup, login, and CRUD features

## Quick Start
./mvnw spring-boot:run

Visit: http://localhost:9999

## Default pages:

- /signup → Create account
- /login → Login
- /home → Files, Notes, Credentials

## Demo
Below is a walkthrough of the app features:
- **Signup & Login**: Secure user authentication
- **Files tab**: Upload, view, and delete files
- **Notes tab**: Create, update, delete notes
- **Credentials tab**: Store & encrypt website credentials

## Screenshots
### Signup page 
<img width="1280" height="797" alt="image" src="https://github.com/user-attachments/assets/93e65011-52ae-458d-9c19-c361fd7dbbb9" />

### Login page
<img width="556" height="576" alt="image" src="https://github.com/user-attachments/assets/a39eb0af-294c-4fbe-bb45-bb33d6c52199" />

Error message if wrong password: <img width="560" height="552" alt="image" src="https://github.com/user-attachments/assets/89195678-8064-4be9-af01-f86ab29033f8" />

### Home page – Files tab
<img width="1468" height="377" alt="image" src="https://github.com/user-attachments/assets/42c12b30-e34b-49e7-ad33-8ca37f676044" />

### Home page – Notes tab
<img width="1577" height="425" alt="image" src="https://github.com/user-attachments/assets/cf60cd70-b215-48c0-9579-b42ca2ecb826" />

### Home page – Credentials tab
<img width="1472" height="393" alt="image" src="https://github.com/user-attachments/assets/d49e627b-564c-4463-a254-084d865d4274" />

## Testing
- Unit tests with JUnit & Mockito
- Selenium tests for:
 - Unauthorized access restrictions
 - User signup + login + logout flow
 - Notes: create, update, delete
 - Credentials: store, encrypt/decrypt, delete

## Project Structure
- `controller/` → Spring MVC controllers
- `service/` → Services like HashService, EncryptionService
- `mapper/` → MyBatis mappers for DB access
- `model/` → POJOs for Files, Notes, Credentials, Users
- `resources/templates/` → Thymeleaf HTML pages
- `resources/static/` → CSS, JS


