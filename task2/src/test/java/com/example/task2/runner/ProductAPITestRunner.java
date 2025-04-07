package com.example.task2.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/ProductAPI.feature",
        glue = "com.example.task2.stepdefinitions",
//        monochrome = true,
        plugin = {"pretty"
                ,"html:target/cucumber-reports/ProductAPI.html"
                ,"json:target/cucumber-reports/ProductAPI.json"},tags = "@ExcelDriven1" // Run only Excel-based tests

)
public class ProductAPITestRunner extends AbstractTestNGCucumberTests {
//    @Override
//    @DataProvider(parallel = true)  // Enables parallel execution in a single test batch
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}
