@ExcelDriven1
Feature: ProductAPI
  @CreateProducts
  Scenario: Create products using Excel
    Given I run create product tests from "CreateProductSuccess"

  @CreateProductNegative
  Scenario: Create product failures using Excel
    Given I run create product tests from "CreateProductFailure"

  @GetProducts
  Scenario: Get products by ID using Excel
    Given I run get product by id tests from "GetProductById"


