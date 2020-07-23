package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.model.User;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The UserContext class holds the functionality
 * specifically relating to the user endpoint
 */

@Component
public class UserContext extends CommonContext {

    private List<User> users;
    private int userId;
    private HttpRestClient httpRestClient;

    @Autowired
    public UserContext(HttpRestClient httpRestClient) {
        this.httpRestClient = httpRestClient;
    }

    /**
     * This method is used to parse JSON string receiving as an API response
     * and map it on the User.class as List and saves it in the users variable
     */
    public void parseJsonResponseOfUsers() throws JsonProcessingException {
        users = mapFromJsonList(httpRestClient.getResponseBody().asString(), User.class);
    }

    /**
     * This method checks if the user is found in the list of users
     */
    public boolean isUserFound(String userName) {
        return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(userName));
    }

    /**
     * This method fetch the user id and saves it in the userId variable
     */
    public void setUserId(String userName) {
        userId = users.stream().filter(user -> user.getUsername().equalsIgnoreCase(userName)).
                findFirst().map(User::getId).get();
    }

    public int getUserId() {
        return userId;
    }

}
