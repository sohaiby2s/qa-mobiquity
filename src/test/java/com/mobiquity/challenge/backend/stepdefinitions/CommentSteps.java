package com.mobiquity.challenge.backend.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.context.CommentContext;
import com.mobiquity.challenge.backend.context.PostsContext;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.Method;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;


public class CommentSteps {

    @Autowired
    private CommentContext commentContext;

    @Autowired
    private PostsContext postsContext;

    @Autowired
    private HttpRestClient httpRestClient;

    @And("Client calls the {string} method of {string} endpoint by appending each post id as parameter")
    public void clientCallsTheMethodOfEndPointByAppendPosts(String method, String endPoint) throws JsonProcessingException {
        for (int id : postsContext.getPostIds()) {
            httpRestClient.sendHttpRequest(Method.valueOf(method), endPoint + "?postId=" + id);
            commentContext.parseJsonResponseOfComments();
        }
    }

    @Then("Email address on each comment should be in proper format")
    public void emailAddressOnEachCommentShouldBeInProperFormat() {
        Assert.assertTrue("Email Address is not in proper format", commentContext.checkEmailAddressFormat());
    }


}
