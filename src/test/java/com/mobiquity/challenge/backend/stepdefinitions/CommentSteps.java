package com.mobiquity.challenge.backend.stepdefinitions;

import com.mobiquity.challenge.backend.context.CommentContext;
import com.mobiquity.challenge.backend.context.CommonContext;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentSteps {

    @Autowired
    private CommentContext commentContext;

    @And("User gathers comments on each post")
    public void userGathersCommentsOnEachPost(){
        commentContext.gatherCommentsData();
    }

    @Then("Email address on each comment should be in proper format")
    public void emailAddressOnEachCommentShouldBeInProperFormat(){
        Assert.assertTrue("Email Address is not in proper format",commentContext.checkEmailAddressFormat());
    }
}
