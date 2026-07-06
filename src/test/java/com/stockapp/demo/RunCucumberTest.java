package com.stockapp.demo;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.stockapp.demo.steps",
                "com.stockapp.demo.config"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        }
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}