package com.mobiquity.challenge.backend.context;

import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonContext {

    @Autowired
    private HttpRestClient httpRestClient;

    private ResponseBody responseBody;
    private Integer statusCode;

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean checkEmailFormat(String email){
        String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(emailRegex);
    }

}
