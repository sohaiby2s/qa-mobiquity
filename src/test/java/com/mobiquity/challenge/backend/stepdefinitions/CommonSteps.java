package com.mobiquity.challenge.backend.stepdefinitions;

import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonSteps {

    @Autowired
    private HttpRestClient httpRestClient;


    @Given("Client calls the {string} method of {string} endpoint")
    public void clientCallsTheEndPoint(String method, String endpoint) {
        httpRestClient.sendHttpRequest(Method.valueOf(method), endpoint);
    }

    @Then("Response code {int} is returned")
    public void responseCodeIsReturned(Integer statusCode) {
        Assert.assertEquals("Response status code is not " + statusCode,
                statusCode, httpRestClient.getStatusCode());
    }

}
