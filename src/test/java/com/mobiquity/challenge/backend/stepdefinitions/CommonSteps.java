package com.mobiquity.challenge.backend.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import com.mobiquity.challenge.common.ValidationUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Method;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;


public class CommonSteps {

    private final HttpRestClient httpRestClient;

    @Autowired
    public CommonSteps(HttpRestClient httpRestClient) {
        this.httpRestClient = httpRestClient;
    }

    @Given("Client calls the {string} method of {string} endpoint")
    public void clientCallsTheEndPoint(String method, String endpoint) {
        httpRestClient.initRestAPI();
        httpRestClient.sendHttpRequest(Method.valueOf(method), endpoint);
    }

    @Then("Response code {int} is returned")
    public void responseCodeIsReturned(Integer statusCode) {
        Assert.assertEquals("Response status code is not " + statusCode,
                statusCode, httpRestClient.getStatusCode());
    }

    @Then("Api should return empty response")
    public void apiShouldReturnEmptyList() throws JsonProcessingException {
        Assert.assertTrue("Api response is not empty",
                ValidationUtils.ifBodyEmpty(httpRestClient.getResponseBody().asString()));
    }

}
