package com.mobiquity.challenge.backend.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.challenge.common.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * HttpRestClient is use to call Rest Api and perform all the functions
 * specifically relating to the API requests and responses
 */

@Component
public class HttpRestClient {

    private RequestSpecification requestSpecification;
    private ResponseBody responseBody;
    private Integer statusCode;
    private Response response;

    public HttpRestClient() {
        RestAssured.baseURI = ConfigReader.API_URL;
    }

    public void initRestAPI() {
        requestSpecification = RestAssured.given();
    }

    /**
     * This method calls the Rest API
     */
    public void sendHttpRequest(Method method, String endpoint) {
        response = requestSpecification.request(method, endpoint);
        setResponseBody(response.getBody());
        setStatusCode(response.getStatusCode());
    }

    public void setBody(Object body) {
        requestSpecification.body(body);
    }

    public void setQueryParam(String key, Object value) {
        requestSpecification.queryParam(key, value);
    }

    public void addHeader(String header, String value) {
        requestSpecification.header(header, value);
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    private void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    private void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> ifBodyIsEmpty() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(getResponseBody().asString(), new TypeReference<List<String>>() {
        });
    }

}
