package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.challenge.backend.model.User;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserContext {

    private static final String USER = "/users";

    private List<User> users;
    private int userId;

    @Autowired
    private HttpRestClient httpRestClient;

    @Autowired
    private CommonContext commonContext;

    public void callGetUserApi() {
        httpRestClient.sendHttpRequest(Method.GET, USER);
        setUser();
    }

    private void setUser(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            users = objectMapper.readValue(commonContext.getResponseBody().asString(), new TypeReference<List<User>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserFound(String userName){
        return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(userName));
    }

    public String getName(String userName){
        return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(userName)).
                findFirst().map(User::getUsername).get();
    }

    public void setUserId(String userName){
        userId = users.stream().filter(user -> user.getUsername().equalsIgnoreCase(userName)).
                findFirst().map(User::getId).get();
    }

    public int getUserId(){
        return userId;
    }

}
