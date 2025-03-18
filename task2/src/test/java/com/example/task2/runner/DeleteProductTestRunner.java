package com.example.task2.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/DeleteProduct.feature",
        glue = "com.example.task2.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports/DeleteProduct.html"}
)
public class DeleteProductTestRunner extends AbstractTestNGCucumberTests {
}
