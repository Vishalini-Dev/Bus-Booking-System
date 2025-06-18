# 🚌 Bus Ticket Booking System

A full-stack **Bus Ticket Booking System** built using Spring Boot, MySQL, HTML, CSS, and JavaScript. The system allows users to register, log in, search for buses, book tickets, and manage profiles.

## 📌 Features

- 🔐 User registration & login with validations
- 🔍 Search for available buses by source, destination, and date
- 🧾 Book and manage tickets
- 👤 User profile and booking history
- 📄 RESTful API integration

## 💻 Tech Stack

| Technology     | Description                          |
|----------------|--------------------------------------|
| Backend        | Java, Spring Boot                    |
| Frontend       | HTML, CSS, JavaScript (Vanilla)      |
| Database       | MySQL                                |
| ORM Framework  | Spring Data JPA                      |
| Tools          | Maven, Postman, Git                  |

## 🔧 Setup Instructions

### Prerequisites

- Java 17+
- MySQL
- Maven
- IDE (IntelliJ, Eclipse, or VS Code)

### Backend Setup (Spring Boot)

1. Clone the repository:
   git clone https://github.com/your-username/bus-booking-system.git
   cd bus-booking-system
   
2. Create a MySQL database:
   CREATE DATABASE bus_booking;
   
3. Update your application.properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/bus_booking
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

4. Run the application:
   mvn spring-boot: run

### Frontend Setup

1. Navigate to the frontend/ directory:
   cd frontend
   
2. Open index.html in your browser or run a live server (VS Code Live Server Extension).

3.API Testing
   Use Postman or any REST client to test endpoints such as:

   I)    POST /api/auth/register

   II)   POST /api/auth/login

   III)  GET /api/buses

🧩 Project Structure
   
bus-booking-system/
│
├── src/
│   ├── main/
│   │   ├── java/com/busbooking/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── (HTML, CSS, JS)
│
├── frontend/
│   └── (HTML, CSS, JS files)
├── pom.xml
└── README.md

🧩 Frontend Integration
     The frontend (HTML, CSS, JavaScript) is connected to the Spring Boot backend using CORS.
     If you're setting up the project, you can place your frontend files directly inside the src/main/resources/static folder of the Spring Boot project. This allows Spring Boot to serve your frontend automatically when the application runs.
     📂 Path: src/main/resources/static

🧑‍💻 Author --> Vishalini-Dev


   

