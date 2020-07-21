package com.mobiquity.challenge.backend.stepdefinitions;

import com.mobiquity.challenge.backend.context.CommonContext;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonSteps {

    @Autowired
    private CommonContext commonContext;

    @Then("Response code {int} is returned")
    public void responseCodeIsReturned(Integer statusCode) {
        Assert.assertEquals("Response status code is not " + statusCode,
                statusCode, commonContext.getStatusCode());
    }

}
