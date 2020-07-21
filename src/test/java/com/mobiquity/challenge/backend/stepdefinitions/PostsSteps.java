package com.mobiquity.challenge.backend.stepdefinitions;

import com.mobiquity.challenge.backend.context.PostsContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class PostsSteps {

    @Autowired
    private PostsContext postsContext;

    @And("User calls the posts endpoint based on the data gathered")
    public void userCallsThePostsEndPointBasedOnTheDataGathered(){
        postsContext.callGetPostsApi();
    }

    @Then("Count of user's post is {int}")
    public void countOfUserPostIs(int count){
        Assert.assertEquals("Count of user's post is not "+count,count,postsContext.getPostCount());
    }

    @And("User gathers all the posts based on the user id")
    public void userGathersThePostsBasedOnDataGathered(){
        postsContext.gathersPostId();
    }

}
