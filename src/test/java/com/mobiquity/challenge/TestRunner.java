package com.mobiquity.challenge;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = {"com/mobiquity/challenge/backend/stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-html-reports", "json:target/cucumber-reports.json"})

public class TestRunner {
}
