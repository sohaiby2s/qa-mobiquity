package com.mobiquity.challenge.backend.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.context.PostsContext;
import com.mobiquity.challenge.backend.context.UserContext;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.Method;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class PostsSteps {


    private final PostsContext postsContext;
    private final UserContext userContext;
    private final HttpRestClient httpRestClient;

    @Autowired
    public PostsSteps(PostsContext postsContext, UserContext userContext, HttpRestClient httpRestClient) {
        this.postsContext = postsContext;
        this.userContext = userContext;
        this.httpRestClient = httpRestClient;
    }


    @Then("Count of user's post should be {int}")
    public void countOfUserPostIs(int count) {
        Assert.assertEquals("Count of user's post is not " + count, count, postsContext.getPostCount());
    }

    @And("User collects all posts against the user id from the JSON response")
    public void userGathersThePostsBasedOnDataGathered() throws JsonProcessingException {
        postsContext.parseJsonResponseOfPosts();
        postsContext.gathersPostId();
    }

    @And("Client calls the {string} method of {string} endpoint by appending {string} as parameter")
    public void clientCallsTheMethodOfEndPointByAppendingUserIdAsParameter(String method, String endPoint, String parameterValue) {
        httpRestClient.initRestAPI();
        httpRestClient.setQueryParam(parameterValue, String.valueOf(userContext.getUserId()));
        httpRestClient.sendHttpRequest(Method.valueOf(method), endPoint);
    }

}
