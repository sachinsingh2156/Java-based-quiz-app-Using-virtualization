# Java-Based Quiz Application with MySQL Database

## Project Overview

This project is a **Java-based Quiz Application** that utilizes **JFrame for the graphical user interface (GUI)** and **MySQL for database management**, running in a **virtualized environment using VirtualBox**. The application allows users to take quizzes, track scores, and manage quiz questions through an admin panel. It demonstrates **GUI application development, database integration, and inter-VM networking**.

## Features

### For Users (Players)

- User Registration & Login
- Participate in quizzes with multiple difficulty levels
- View and track quiz scores
- Interactive GUI-based quiz interface

### For Administrators

- Secure admin login
- Add, update, and delete quiz questions
- Manage quiz difficulty levels
- Monitor player performance and reports

## System Architecture

### Overview

This application is designed using a **client-server architecture**, where the **Java Quiz Application (Client)** interacts with the **MySQL Database (Server)** using JDBC.

### Architecture Diagram

![Java-Based Quiz Application (1)](https://github.com/user-attachments/assets/0373f53d-be4a-48b7-a379-43a2c405ff28)


## Technologies Used

| Component               | Technology Used    |
| ----------------------- | ------------------ |
| Programming             | Java (JFrame)      |
| Database                | MySQL (MariaDB)    |
| Connectivity            | JDBC               |
| Virtualization          | VirtualBox         |
| Operating System        | Debian Linux       |
| Development Environment | VS Code / IntelliJ |

## Project Structure

```
📦 quiz-app
├── 📁 src
│   ├── 📁 controller       # Handles business logic
│   ├── 📁 db               # Database connection & queries
│   ├── 📁 model            # Java classes for Players, Questions, Reports
│   ├── 📁 utils            # Utility functions (password hashing, validation)
│   ├── 📁 view             # JFrame UI screens
│   └── README.md           # Project documentation
```

## Installation Guide

### Step 1: Set Up Virtual Machines (VMs)

- Install **VirtualBox** and create two **VMs**:
  - **VM1**: Java Application Server
  - **VM2**: MySQL Database Server
- Configure **internal networking** between the VMs.

### Step 2: Install MySQL on VM2 (Database Server)

Run the following commands in **VM2**:

```sh
sudo apt update
sudo apt install mysql-server -y
```

Edit MySQL configuration file to allow remote access:

```sh
sudo nano /etc/mysql/mysql.conf.d/mysqld.cnf
```

Change:

```ini
bind-address = 0.0.0.0
```

Grant access for VM1:

```sql
GRANT ALL PRIVILEGES ON quiz_app.* TO 'r'root@'%' IDENTIFIED BY 'password123';
FLUSH PRIVILEGES;
```

Restart MySQL:

```sh
sudo systemctl restart mysql
```

### Step 3: Set Up Java Application on VM1

Install Java Development Kit (JDK):

```sh
sudo apt update
sudo apt install default-jdk -y
```

Download the MySQL JDBC Connector:

```sh
wget https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar
```

### Step 4: Clone & Run the Application

```sh
git clone git https://github.com/sachinsingh2156/Java-based-quiz-app-Using-virtualization.git
cd quiz-app
javac -cp .:mysql-connector-java-8.0.28.jar src/App.java
java -cp .:mysql-connector-java-8.0.28.jar App
```

## Troubleshooting

### Issue: MySQL Connection Refused

Check if MySQL is running:

```sh
sudo systemctl status mysql
```

Ensure MySQL is listening on the correct port:

```sh
sudo netstat -tulnp | grep mysql
```

### Issue: Java Application Cannot Connect to MySQL

Allow MySQL traffic through the firewall:

```sh
sudo ufw allow 3306/tcp
sudo ufw reload
```

## Contributing

1. Fork the repository.
2. Create a new branch:
   ```sh
   git checkout -b feature-branch
   ```
3. Commit your changes:
   ```sh
   git commit -m "Added new feature"
   ```
4. Push to GitHub:
   ```sh
   git push origin feature-branch
   ```
5. Submit a Pull Request.

##
