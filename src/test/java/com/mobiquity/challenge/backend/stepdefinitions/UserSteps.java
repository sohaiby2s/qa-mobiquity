package com.mobiquity.challenge.backend.stepdefinitions;

import com.mobiquity.challenge.backend.context.UserContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSteps {

    @Autowired
    private UserContext userContext;

    @Given("User calls the users endpoint")
    public void userCallsTheGetUserApi(){
        userContext.callGetUserApi();
    }

    @Then("{string} user name found in user data")
    public void userNameFoundInUserData(String userName){
        Assert.assertTrue(userName+" is not found in user data",userContext.isUserFound(userName));
    }

    @And("User gathers the data of user name {string}")
    public void userGetsTheIdOfAUser(String userName){
        userContext.setUserId(userName);
    }
}
