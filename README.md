# Consistent.bino

## Introduction
Consistent.bino is a project that aims to help track your progression with current goals you may have in life and help visualize how far/deep you are in your journey.

## Demo

Check out the demo video below to see how the application works in action:

Click the image above or [watch the video here](https://youtu.be/HYIML1i0IfM).

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- IDE / Text Editor
- PostgreSQL
- Docker

### Steps
1. **Clone the repository:**
   ```sh
   git clone https://github.com/samiyeb/consistent.bino.git
   cd consistent.bino

2. **Setup/Create the application.properties file:**
   ```sh
   spring.sql.init.mode = always
   spring.jpa.hibernate.ddl-auto = update
   spring.datasource.url = jdbc:postgresql://localhost:5432/<your psql username>
   spring.datasource.username = <your psql username>
   spring.datasource.password = <your psql password>
   spring.jpa.show-sql: true

3. **Setup/Create the compose.yml file:**
   ```sh
   services:
     postgres:
       container_name: 'guide-postgres'
       image: 'postgres:latest'
       environment:
         - 'POSTGRES_DB=YOUR_DB'
         - 'POSTGRES_USER=YOUR_USERNAME'
         - 'POSTGRES_PASSWORD=YOUR_PASSWORD'
       ports:
         - '5432:5432'

4. **Run the application:**

   ```sh
   ./mvnw spring-boot:run

- If you encounter a "Permission denied" error, you may need to give the `mvnw` script executable permissions on bash:

   ```sh
   chmod +x ./mvnw

### Usage
Once the application is running, you can access it at http://localhost:8080. 

## Features
* Goal setting and bookkeeping
* Goal progress tracking
* Progress visualization (HTML, CSS [Bootstrap], Javascript)

