package com.example.task2.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/DeleteProduct.feature",
        glue = "com.example.task2.stepdefinitions",
//        monochrome = true,
        plugin = {"pretty"
                , "html:target/cucumber-reports/DeleteProduct.html"
                 ,"json:target/cucumber-reports/DeleteProduct.json"},tags = "@ExcelDriven2" // Run only Excel-based tests


)
public class DeleteProductTestRunner extends AbstractTestNGCucumberTests {
//    @Override
//    @DataProvider(parallel = true)  // Enables parallel execution in a single test class
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}
