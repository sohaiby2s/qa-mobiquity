package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.model.Comment;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The CommentContext class holds the functionality
 * specifically relating to the Comment endpoint
 */

@Component
public class CommentContext extends CommonContext {

    private List<Comment> comments = new ArrayList<>();

    @Autowired
    private HttpRestClient httpRestClient;

    /**
     * This method is used to parse JSON string receiving as an API response
     * and map it on the Comment.class as List and append it into the comments
     */

    public void parseJsonResponseOfComments() throws JsonProcessingException {
        comments.addAll(mapFromJsonList(httpRestClient.getResponseBody().asString(), Comment.class));
    }

    /**
     * This method is used for fetching all the email addresses form comments
     */
    public List<String> getEmailAddress() {
        return comments.stream().map(Comment::getEmail).filter(email -> !checkEmailFormat(email)).
                collect(Collectors.toList());
    }

}
