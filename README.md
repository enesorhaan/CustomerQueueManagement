# Customer Queue Management System

The Customer Queue Management System is a JavaFX-based application designed to manage customer queues efficiently. This system allows users to register new customers, log in using an ID or phone number, and add customers to a queue for different services. The primary goal is to streamline the customer service process by maintaining an organized queue and ensuring that each customer is served in the order they arrive.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [System Architecture](#system-architecture)
- [Data Structures](#data-structures)
- [Challenges and Solutions](#challenges-and-solutions)
- [Contributing](#contributing)
- [License](#license)

## Features

- User-friendly interface for customer registration and login.
- Management of customer queues using appropriate data structures.
- Organized handling of different customer service transactions.
- Data persistence by integrating the application with a MySQL database.
- Efficient customer lookup using HashMap for quick access by ID or phone number.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/customer-queue-management.git
    ```
2. Navigate to the project directory:
    ```bash
    cd customer-queue-management
    ```
3. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
4. Ensure you have JavaFX and JDBC libraries added to your project.
5. Set up the MySQL database and update the database connection parameters in `DatabaseController.java`.

## Usage

1. Run the main application:
    ```java
    Main.java
    ```
2. Use the interface to:
    - Register new customers.
    - Log in using customer ID or phone number.
    - Add customers to the queue for specific transactions.
3. Utilize the queue management system to ensure customers are served in the correct order.

## System Architecture

The system is composed of several components:

- **Main Application**: Manages the primary application flow and UI transitions.
- **Controllers**: Handle user interactions and business logic.
- **Database Controller**: Manages database connections and queries.
- **Models**: Represent the data structures used within the application (e.g., `Customer` class).

## Data Structures

- **HashMap**: Used to store and retrieve customer information quickly using ID and phone number.
- **Queue**: Used to manage the order of customers waiting for service.

## Challenges and Solutions

- **Database Connection Management**: Ensuring robust handling of database connections and queries. This was managed by encapsulating the database logic within the `DatabaseController` class.
- **Concurrency Issues**: Handling simultaneous access and modifications to the queue. This was mitigated by ensuring synchronized access to the queue.

## Contributing

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure your code follows the project style and include tests where appropriate.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
