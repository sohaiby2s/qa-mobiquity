package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.challenge.backend.model.Comment;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentContext extends CommonContext {

    private List<Comment> comments = new ArrayList<>();

    @Autowired
    private HttpRestClient httpRestClient;

    public void parseJsonResponseOfComments() throws JsonProcessingException {
        comments.addAll(mapFromJsonList(httpRestClient.getResponseBody().asString(), Comment.class));
    }

    public List<String> getEmailAddress() {
        return comments.stream().map(Comment::getEmail).filter(email -> !checkEmailFormat(email)).
                collect(Collectors.toList());
    }

}
