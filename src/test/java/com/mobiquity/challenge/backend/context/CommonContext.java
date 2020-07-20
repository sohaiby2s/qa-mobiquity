package com.mobiquity.challenge.backend.context;

import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.restassured.path.json.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonContext {

    @Autowired
    private HttpRestClient httpRestClient;

    private JsonPath responseBody;
    private Integer statusCode;

    public JsonPath getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(JsonPath responseBody) {
        this.responseBody = responseBody;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
