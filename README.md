# Consistent.bino

## Introduction
Consistent.bino is a project that aims to help track your progression with current goals you may have in life and help visualize how far/deep you are in your journey.

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- IDE / Text Editor
- PostgreSQL

### Steps
1. **Clone the repository:**
   ```sh
   git clone https://github.com/samiyeb/consistent.bino.git
   cd consistent-bino

2. **Setup/Create the application.properties file:**
   ```sh
   spring.sql.init.mode = always
   spring.jpa.hibernate.ddl-auto = update
   spring.datasource.url = `<your pqsl url>`
   spring.datasource.username = `<your psql username>`
   spring.datasource.password = `<your psql password>`
   spring.jpa.show-sql: true

3. **Run the application:**
   ```sh
   ./mvnw spring-boot:run


### Usage
Once the application is running, you can access it at http://localhost:8080. Here, you can create an account, set your goals, and start tracking your progress.

## Features (In progress)
* User authentication and authorization
* Goal setting and tracking
* Progress visualization
* Daily reminders and notifications
* Reporting and analytics on goal progress

## Contributing
### Guidelines
We welcome contributions! Here are some guidelines to help you get started:

1. Fork the repository.
2. Create a new branch (git checkout -b feature/your-feature-name).
3. Make your changes and commit them (git commit -m 'Add some feature').
4. Push to the branch (git push origin feature/your-feature-name).
5. Open a pull request.

Please ensure your code follows our coding conventions and is well-tested.

