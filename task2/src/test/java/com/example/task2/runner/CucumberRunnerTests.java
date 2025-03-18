package com.example.task2.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features/"}, glue = {"com.example.task2.stepdefinitions"},
        plugin = {})
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}
