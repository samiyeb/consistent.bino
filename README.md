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
   cd consistent.bino

2. **Setup/Create the application.properties file:**
   ```sh
   spring.sql.init.mode = always
   spring.jpa.hibernate.ddl-auto = update
   spring.datasource.url = postgresql://localhost:5432/<your psql username>
   spring.datasource.username = <your psql username>
   spring.datasource.password = <your psql password>
   spring.jpa.show-sql: true

3. **Run the application:**
   ```sh
   ./mvnw spring-boot:run


### Usage
Once the application is running, you can access it at http://localhost:8080. 

## Features
* Goal setting and bookkeeping
* Goal progress tracking 
### (IN PROGRESS)
* Reporting and analytics on goal progress
* User authentication and authorization  (Spring Security*)
* Progress visualization (React.js)
* Daily reminders and notifications

## Patch Notes
### Version 1.0.3
Completed backend prototype for the Goal tracking progression application by having users
with a specfic id to create a goal and create tasks for that goal. More updates will come 
for backend, frontend development will be introduced to the project shortly!
### Version 1.0.2
Temporarily disabled the implementation of Spring HATEOAS to revamp system design, In
future versions, the enhanced RESTful service capabilties and improved API evolution
support with hypermedia-driven outputs will make its return soon!
### Version 1.0.1
Implemented Spring HATEOAS to enhance RESTful service capabilities.
Improved API evolution support with hypermedia-driven outputs.
### Version 1.0.0
Initial release with goal setting and bookkeeping features.

## Contributing
### Guidelines
We welcome contributions! Here are some guidelines to help you get started:

1. Fork the repository.
2. Create a new branch (git checkout -b feature/your-feature-name).
3. Make your changes and commit them (git commit -m 'Add some feature').
4. Push to the branch (git push origin feature/your-feature-name).
5. Open a pull request.

Please ensure your code follows our coding conventions and is well-tested.

