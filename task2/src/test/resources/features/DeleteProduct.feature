@ExcelDriven2
Feature: Product Deletion

  @DeleteProducts
  Scenario: Delete products using Excel
    Given I run delete product tests from "DeleteProduct"

  @DeleteProductNegative
  Scenario: Delete non-existing product using Excel
    Given I run delete product tests from "ProductNotFound"
