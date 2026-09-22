# StudySync – Student Study & Productivity Platform

StudySync is a Java-based web application designed to help students organize and manage their academic activities in one place.

The platform provides features for managing students, mentors, subjects, study plans, study sessions, tasks, notes, goals, progress, notifications, and mentor-student assignments.

## Features

### Student Features

- Student registration and login
- Student dashboard
- View available subjects
- Create and manage study plans
- Save study sessions
- Manage tasks
- Add and manage notes
- Set academic goals
- Track study progress
- View notifications

### Mentor Features

- Mentor management
- Mentor profile information
- Assign students to mentors
- View assigned students
- Manage mentor-student relationships

### Admin Features

- Admin login
- Admin dashboard
- View total students
- View total mentors
- View available subjects
- Manage student information
- Manage mentor information
- Manage subjects
- View mentor-student assignments

## Technologies Used

- Java
- JDBC
- Servlets
- MySQL
- HTML
- CSS
- Apache Tomcat
- Eclipse IDE

## Project Architecture

The project follows the DAO (Data Access Object) design pattern to separate database operations from application logic.

```text
StudySync
│
├── com/
│   └── studysync/
│       ├── DAO/
│       ├── DAOImpl/
│       ├── model/
│       ├── servlet/
│       └── util/
│
└── webapp/
    ├── HTML Pages
    ├── CSS
    └── Web Resources
```

### Package Description

**DAO**

Contains interfaces that define database operations.

**DAOImpl**

Contains implementations of DAO interfaces using JDBC.

**model**

Contains Java model classes representing application entities.

**servlet**

Contains Servlets responsible for handling HTTP requests and application logic.

**util**

Contains utility classes such as database connection handling.

## Database

The application uses MySQL as the database.

Main database tables include:

```text
user
mentor
mentor_student
subject
task
study_plan
study_session
note
notification
progress
suggestion
goal
```

## How to Run the Project

### 1. Clone or Download the Repository

Download or clone the repository and open the project in Eclipse.

### 2. Configure MySQL

Create a MySQL database named:

```sql
studysync
```
Create the required tables and insert the necessary data.

### 3. Configure Database Connection

Update the database connection details in the database connection utility according to your local MySQL setup.

> Do not upload real database passwords or other sensitive credentials to GitHub.

### 4. Configure Apache Tomcat

Use Apache Tomcat 10 or a compatible Tomcat version.

Add the project to the Tomcat server in Eclipse.

### 5. Run the Application

Start the Tomcat server and open:

```text
http://localhost:8080/StudySync/
```

## Project Highlights

- Built using Java Web Technologies
- Implements the DAO design pattern
- Uses JDBC for database connectivity
- Uses MySQL for data storage
- Uses Servlets for server-side request handling
- Includes separate student, mentor, and admin functionality
- Provides a structured academic productivity platform

## Future Enhancements

- Online study reminders
- Advanced progress reports
- Calendar integration
- Email notifications
- More detailed student analytics
- Improved responsive UI
- Deployment to a cloud platform

## Author

**Sanjana Hatture**

### Connect With Me

- GitHub: [sanjanah16](https://github.com/sanjanah16)
- LinkedIn: [Sanjana Hatture](https://www.linkedin.com/in/sanjana-hatture-549a93302/)
- Email: sanjanah2004@gmail.com













