package com.mobiquity.challenge.backend.restclient;

import com.mobiquity.challenge.common.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

@Component
public class HttpRestClient {

    private RequestSpecification requestSpecification;
    private ResponseBody responseBody;
    private Integer statusCode;
    private Response response;

    public HttpRestClient() {
        RestAssured.baseURI = ConfigReader.API_URL;
    }

    public void sendHttpRequest(Method method, String endpoint) {

        requestSpecification = RestAssured.given();
        response = requestSpecification.request(method, endpoint);
        setResponseBody(response.getBody());
        setStatusCode(response.getStatusCode());

    }

    public void setBody(Object body) {
        requestSpecification.body(body);
    }

    public void setPathParam(String key, Object value){
        requestSpecification.pathParam(key,value);
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

}
