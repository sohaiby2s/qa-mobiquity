package com.mobiquity.challenge.backend.restclient;

import com.mobiquity.challenge.backend.context.CommonContext;
import com.mobiquity.challenge.common.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpRestClient {

    @Autowired
    private CommonContext commonContext;

    private RequestSpecification requestSpecification;

    public HttpRestClient() {
        RestAssured.baseURI = ConfigReader.API_URL;
        requestSpecification = RestAssured.given();
    }

    public void setBody(Object body) {
        requestSpecification.body(body);
    }

    public void addHeader(String header, String value) {
        requestSpecification.header(header, value);
    }

    public Response sendHttpRequest(Method method, String endpoint) {
        Response response = requestSpecification.request(method, endpoint);
        commonContext.setResponseBody(response.getBody().jsonPath());
        commonContext.setStatusCode(response.getStatusCode());
        return response;
    }

}
