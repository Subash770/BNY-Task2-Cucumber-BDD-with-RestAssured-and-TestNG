# BNY-Task2-Cucumber-BDD-with-RestAssured-and-TestNG

This project demonstrates API testing using **Cucumber BDD**, **RestAssured**, and **TestNG** for an eCommerce product management application. The application allows the creation, retrieval, and deletion of products through a set of RESTful API endpoints. The tests are written with a Behavior-Driven Development (BDD) approach using **Cucumber** for better readability and ease of collaboration.

Now enhanced with **Excel-based Data-Driven Testing** using **Apache POI** to handle test data efficiently.

---

## 🛠️ Project Setup

### Prerequisites
- **Java 8+**
- **Maven**
- **TestNG**
- **Apache POI** (for Excel reading)
- **RestAssured**
- **Cucumber**
- **IDE** (IntelliJ IDEA or Eclipse)

### Installation

```bash
git clone https://github.com/Subash770/BNY-Task2-Cucumber-BDD-with-RestAssured-and-TestNG.git
cd BNY-Task2-Cucumber-BDD-with-RestAssured-and-TestNG
mvn clean install
```

---

## 📁 Directory Structure

```
src/
├── main/java/com/example/task2/             # Core business logic
├── test/java/com/example/task2/stepdefinitions/  # Cucumber step definitions
├── test/java/com/example/task2/utils/       # Excel reader utility
├── test/resources/features/                 # Feature files
└── test/resources/testdata/                 # Excel files
```

---

## 🧩 Key Components

### ✅ Cucumber BDD Feature Files
Located in `src/test/resources/features`, these files define test scenarios for:
- Creating products
- Fetching product details
- Deleting products

### ✅ Step Definitions
Located in `src/test/java/com/example/task2/stepdefinitions/`, step definitions use **RestAssured** to send HTTP requests and validate API responses.

### ✅ Excel Data Support
Excel files (in `.xlsx` format) are located under `src/test/resources/testdata/`.

A utility class using **Apache POI** reads the data and feeds it to the test scenarios dynamically.

---

## 🧪 Running the Tests

### Option 1: Using Maven

```bash
mvn test
```

### Option 2: Using IDE

Right-click on any runner class (like `ProductAPITestRunner.java` or `DeleteProductTestRunner.java`) and choose **Run**.

---

## 🏷️ Cucumber Tags

To run specific tagged scenarios:

```bash
mvn test -Dcucumber.options="--tags @CreateProduct"
```

Tags available:
- `@CreateProduct`
- `@DeleteProduct`

---

## 📊 Excel-Based Data-Driven Testing

This project uses **Apache POI** to read test input data from Excel files.

### 🔍 Sample Excel File

Located at `src/test/resources/testdata/CreateProductData.xlsx`

| name   | price  | category     | description         | status |
|--------|--------|--------------|---------------------|--------|
| Apple  | 12.5   | Electronics  | High quality apple  | 200    |
| Laptop | 899.99 | Electronics  | High-end laptop     | 200    |

### ✅ Usage in Feature File

```gherkin
@CreateProduct
Scenario: Create products using Excel
  Given I read product test data from "CreateProductData.xlsx"
  When I send the create product request for all Excel rows
  Then the response status for each should match the expected
```

---

## 🧹 Delete Product Excel Scenario

```gherkin
@DeleteProduct
Scenario: Delete products using Excel
  Given I read product IDs from "DeleteProductData.xlsx"
  When I send the delete product request for all Excel rows
  Then the response should match expected messages
```

### 🔍 Sample DeleteProductData.xlsx

| id   | status | message                      |
|------|--------|------------------------------|
| 1    | 200    | Product deleted successfully |
| 999  | 404    | Product not found            |
| 1234 | 404    | Product not found            |

---

## 🤝 Contributing

1. Fork this repository
2. Create a new branch
3. Add your features or tests
4. Submit a pull request with clear changes
