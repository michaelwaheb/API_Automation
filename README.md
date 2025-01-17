# API Testing Framework

This project provides a robust and modular framework for API testing using **RestAssured**, **TestNG**, and Java. The framework includes error handling, reusable utilities, and a clear test structure to simplify API testing.

## Features

- **API Error Handling**: Centralized error handler for validating API responses.
- **TestNG Integration**: Enables efficient and organized test execution.
- **JSON Data Management**: Easily load and validate data from JSON files.
- **Logging and Reporting**: Includes utilities for logging API requests and responses.

---

## Prerequisites

### Software Requirements

1. **Java Development Kit (JDK)**: Version 8 or later.
2. **Maven**: For managing dependencies and running the tests.
3. **IDE**: IntelliJ IDEA, Eclipse, or any Java-supported IDE.
4. **Git**: To clone the repository.

### Dependencies
The project uses the following libraries:

- **RestAssured**: For API interactions.
- **TestNG**: For test execution.
- **Jackson**: For JSON data processing.

All dependencies are managed via Maven.

---

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/michaelwaheb/API_Automation.git
   cd API_Automation
   ```

2. **Open in IDE**:
   - Import the project as a Maven project in your IDE.

3. **Install Dependencies**:
   - Maven will automatically resolve the dependencies when you load the `pom.xml` file. To force download:
     ```bash
     mvn clean install
     ```

---

## Configuration

1. **API Base URL**:
   - Update the base URL in the `BaseTest` class or configuration file (e.g., `src/test/resources/config.properties`).

   Example:
   ```properties
   base.url=https://example.com
   ```

2. **JSON Data**:
   - Add test data files under `src/test/java/Data/`.


---

## Running the Tests

1. **From IDE**:
   - Right-click on the test class or package and select `Run`.

2. **From Command Line**:
   ```bash
   mvn clean test
   ```

---

## Key Classes

1. **ErrorHandler**: Centralized class to handle API errors and response validations.
2. **BaseTest**: Sets up the environment for all tests.
3. **Utils**: Provides reusable methods for printing responses and managing common tasks.

---


