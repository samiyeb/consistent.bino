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

## License
MIT License

Copyright (c) 2024 Sam Bakri

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

