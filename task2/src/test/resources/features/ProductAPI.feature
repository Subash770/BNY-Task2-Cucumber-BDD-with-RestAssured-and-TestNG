Feature: Product API Testing

  @CreateProductSuccess
  Scenario Outline: Create a product successfully
    Given I create a product with name "<name>", price <price>, category "<category>", and description "<description>"
    Then the response status should be 200
    And the response should contain name "<name>", price <price>, category "<category>", and description "<description>"

    Examples:
      | name       | price | category    | description                |
      | Laptop     | 1000  | Electronics | High-end gaming laptop     |
      | Shirt      | 50    | Clothing    | Cotton casual shirt        |
      | Headphones | 150   | Accessories | Noise-canceling headphones |

  @CreateProductFailure
  Scenario Outline: Create a product with missing or invalid details
    Given I create a product with name "<name>", price <price>, category "<category>", and description "<description>"
    Then the response status should be <status>
    And the response should contain error message "<error>"

    Examples:
      | name   | price | category    | description      | status | error                                  |
      |        | 100   | Clothing    | Missing name     | 400    | Name is required                       |
      | Laptop | 0     | Electronics | Invalid price    | 400    | Price is required and must be positive |
      | Shirt1 | -50   | Electronics | Invalid price    | 400    | Price is required and must be positive |
      |        | 50    | Invalid     | Invalid category | 400    | Name is required                       |
      | Laptop | 1000  | Electronics | Duplicate entry  | 400    | Product with this name already exists  |

  @GetProductById
  Scenario Outline: Retrieve a product by ID
    Given I send a request to get product with ID <id>
    Then the response status should be <status>
    And the response should contain name "<name>", price <price>, category "<category>", and description "<description>"

    Examples:
      | id  | name   | price | category    | description            | status |
      | 1   | Laptop | 1000  | Electronics | High-end gaming laptop | 200    |
      | 2   | Shirt  | 50    | Clothing    | Cotton casual shirt    | 200    |

  @ProductNotFound
  Scenario Outline: Retrieve a non-existing product by ID
    Given I send a request to get product with ID <id>
    Then the response status should be <status>
    And the response should contain error message "<error>"

    Examples:
      | id   | status | error             |
      | 999  | 404    | Product not found  |
      | 1234 | 404    | Product not found  |
