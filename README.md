# 🚗 Vehicle Rental System (Java Maven Project)

A console-based vehicle rental system built with **Java 21**, **MySQL 8**, and **Maven**.
Developed using **Eclipse IDE for Enterprise Java and Web Developers (2025-06)**.

![Java](https://img.shields.io/badge/Java-21-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0.41-blue)
![Build](https://img.shields.io/github/actions/workflow/status/SABIHSHAFI/Vehicle_Rental_System-No-UI-/maven.yml?branch=main)
![License](https://img.shields.io/badge/License-MIT-green)

---

## 📋 Requirements

* **Java JDK:** 21 (Tested on 21.0.6 LTS)
* **MySQL Server:** 8.0.41
* **Maven:** Compatible with Java 21
* **IDE (optional):** Eclipse or any Java IDE

---

## 🏗️ Project Structure

```bash
.
├── pom.xml
├── README.md
├── .gitignore
├── src/
│   └── main/
│       └── java/
│           └── (default package)
│               ├── App.java
│               ├── Booking.java
│               ├── BookingDAO.java
│               ├── BookingThread.java
│               ├── DBUtil.java
│               ├── Vehicle.java
│               ├── VehicleDAO.java
│               └── VehicleRentalService.java
```

---

## ⚙️ Setup Instructions

### 1. 📅 Clone the Repository

```bash
git clone https://github.com/SABIHSHAFI/Vehicle_Rental_System-No-UI-.git
cd Vehicle_Rental_System-No-UI-
```

### 2. 🛠️ Create the MySQL Database and Tables

```sql
CREATE DATABASE vehicledb;
USE vehicledb;

CREATE TABLE vehicles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(50),
  model VARCHAR(100),
  number_plate VARCHAR(50),
  is_available TINYINT(1) DEFAULT 1
);

CREATE TABLE bookings (
  booking_id INT PRIMARY KEY,
  vehicle_id INT,
  customer_name VARCHAR(100),
  contact_number VARCHAR(20),
  duration_secs INT,
  booking_time DATETIME,
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

-- Sample Data
INSERT INTO vehicles (type, model, number_plate, is_available) VALUES
('Sedan', 'Honda City', 'ABC1234', 1),
('SUV', 'Hyundai Creta', 'XYZ5678', 1),
('Mini', 'Maruti Alto', 'MNO9999', 1),
('Offroad', 'Mahindra Thar', 'THR001', 1),
('Sedan', 'Toyota Corolla', 'TCO1234', 1);
```

### 3. 🔑 Set Database Credentials

Update `DBUtil.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/vehicledb";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```

---

## 🚀 Build & Run

### Using Maven

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="App"
```

If using Maven Wrapper:

```bash
./mvnw clean install
./mvnw exec:java -Dexec.mainClass="App"
```

Or run directly inside **Eclipse** by right-clicking `App.java` ➔ Run As ➔ Java Application.

---

## 🤖 Features

* View all available vehicles
* Book vehicles by type
* Assign vehicle to customer
* Lock vehicle for booking duration using threads
* View current/active bookings
* Follows DAO (Data Access Object) pattern for modularity and clean separation of logic

---

## 💾 Sample Output

```text
==== Vehicle Rental System ====
1. View Vehicles
2. Book Vehicle
3. View Bookings
4. Exit
Enter choice:
```

---

## 📁 .gitignore

```
# Maven
/target/

# Logs
*.log

# IDEs
*.iml
.idea/
.vscode/

# OS
.DS_Store

# Java
*.class
```

---

## 🛡️ License

This project is licensed under the [MIT License](LICENSE) — free for personal, educational, and research use.

---

## 🔧 GitHub Actions (Optional Workflow)

Create a `.github/workflows/maven.yml` for CI builds:

```yaml
name: Maven Build

on: [push, pull_request]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3

    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with:
        java-version: '21'
        distribution: 'temurin'

    - name: Build with Maven
      run: mvn clean install
```

---

## 🙋 Author

[SABIHSHAFI](https://github.com/SABIHSHAFI)

[AUGSTAY](https://github.com/AugstayGupta)
