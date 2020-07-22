package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.model.User;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserContext extends CommonContext {

    private List<User> users;
    private int userId;

    @Autowired
    private HttpRestClient httpRestClient;


    public void parseJsonResponseOfUsers() throws JsonProcessingException {
        users = mapFromJsonList(httpRestClient.getResponseBody().asString(), User.class);
    }

    public boolean isUserFound(String userName) {
        return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(userName));
    }

    public void setUserId(String userName) {
        userId = users.stream().filter(user -> user.getUsername().equalsIgnoreCase(userName)).
                findFirst().map(User::getId).get();
    }

    public int getUserId() {
        return userId;
    }

}
