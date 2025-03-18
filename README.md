# BNY-Task2-Cucumber-BDD-with-RestAssured-and-TestNG

This project demonstrates API testing using **Cucumber BDD**, **RestAssured**, and **TestNG** for an eCommerce product management application. The application allows the creation, retrieval, and deletion of products through a set of RESTful API endpoints. The tests are written with a Behavior-Driven Development (BDD) approach using **Cucumber** for better readability and ease of collaboration.

## Project Setup

### Prerequisites
- **Java 8+**: Ensure you have Java 8 or above installed.
- **Maven**: The project uses Maven for dependency management.
- **IDE**: Use an IDE like **IntelliJ IDEA** or **Eclipse** for project development.
- **TestNG**: For running the tests.
- **Cucumber**: For BDD framework.
- **RestAssured**: For API testing.
- **Cucumber dependencies** for integration with RestAssured.

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Subash770/BNY-Task2-Cucumber-BDD-with-RestAssured-and-TestNG.git
    ```

2. Navigate into the project directory:
    ```bash
    cd BNY-Task2-Cucumber-BDD-with-RestAssured-and-TestNG
    ```

3. Install dependencies using Maven:
    ```bash
    mvn clean install
    ```

### Directory Structure

- `src/main/java/com/example/task2` – Main Java code for product management APIs.
- `src/test/java/com/example/task2/stepdefinitions/` – Step definitions for Cucumber BDD.
- `src/test/resources/features` – Feature files defining the BDD scenarios.

### Key Components

1. **Cucumber BDD Feature Files**:
    - Located in `src/test/resources/features`.
    - Defines the test scenarios for product management API: creating products, fetching product details, and deleting products.
  
2. **Step Definitions**:
    - Implemented in `src/test/java/com/example/task2/stepdefinitions/`.
    - These classes connect the Cucumber feature steps with the corresponding Java code to send HTTP requests using RestAssured and validate the responses.

3. **TestNG**:
    - Used as the test runner for executing Cucumber feature files.

### Running the Tests

You can run the tests through the following steps:

1. **Using Maven**:  
   Run the following Maven command to run the tests:
    ```bash
    mvn test
    ```

2. **Using an IDE**:
    - If you are using IntelliJ IDEA or Eclipse, you can right-click on the `TestRunner.java` (which contains the Cucumber annotations) and run the tests directly.

### Cucumber Tags

You can run specific scenarios based on tags provided in the feature files, such as:

- `@CreateProduct`
- `@DeleteProduct`
  
To run tests with a specific tag, use the following command:
```bash
mvn test -Dcucumber.options="--tags @CreateProduct"
```

### Feature File Examples

#### Create Product Scenario

```gherkin
@CreateProduct
Scenario Outline: Create a new product
    Given I create a product with name <name>, price <price>, category <category>, and description <description>
    Then the response status should be <status>
    And the response should contain name <name>
    And the response should contain price <price>
    And the response should contain category <category>
    And the response should contain description <description>

Examples:
  | name   | price | category   | description      | status |
  | Apple  | 12.5  | Electronics | High quality apple | 200   |
  | Laptop | 899.99| Electronics | High-end laptop   | 200   |
```

#### Delete Product Scenario

```gherkin
@DeleteProduct
Scenario Outline: Delete a product by ID
    Given I send a delete request for product with ID <id>
    Then the response status should be <status>
    And the response should contain message "<message>"

Examples:
  | id   | status | message                      |
  | 1    | 200    | Product deleted successfully |
  | 999  | 404    | Product not found            |
  | 1234 | 404    | Product not found            |
```

### Contributing

1. Fork this repository.
2. Create a new branch for your changes.
3. Make changes and add tests.
4. Create a pull request describing your changes.
