package com.mobiquity.challenge.backend.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Method;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommonSteps {

    @Autowired
    private HttpRestClient httpRestClient;


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
        ObjectMapper mapper = new ObjectMapper();
        List<String> data = mapper.readValue(httpRestClient.getResponseBody().asString(), new TypeReference<List<String>>() {
        });
        Assert.assertTrue("Api response is not empty", data.isEmpty());
    }

}
