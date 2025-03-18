Feature: Product Deletion

  @DeleteProduct
  Scenario Outline: Delete a product by ID (Positive Case)
    Given I send a delete request for product with ID <id>
    Then the response status should be <status>
    And the response should contain message "<message>"

    Examples:
      | id | status | message                      |
      | 1  | 200    | Product deleted successfully |

  @DeleteProduct
  Scenario Outline: Delete a product by ID (Negative Case)
    Given I send a delete request for product with ID <id>
    Then the response status should be <status>
    And the response should contain error message "<error>"

    Examples:
      | id   | status | error             |
      | 999  | 404    | Product not found |
      | 1234 | 404    | Product not found |
