package com.mobiquity.challenge.backend.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.context.UserContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSteps {

    private final UserContext userContext;

    @Autowired
    public UserSteps(UserContext userContext) {
        this.userContext = userContext;
    }

    @Then("{string} user name found in user list")
    public void userNameFoundInUserData(String userName) {
        Assert.assertTrue(userName + " is not found in user data", userContext.isUserFound(userName));
    }

    @And("User fetch the id of user {string} from user list")
    public void userGetsTheIdOfAUser(String userName) {
        userContext.fetchUserIdByUserName(userName);
    }

    @When("User gets the JSON response from user list")
    public void userGetsTheJsonResponse() throws JsonProcessingException {
        userContext.parseJsonResponseOfUsers();
    }

}
