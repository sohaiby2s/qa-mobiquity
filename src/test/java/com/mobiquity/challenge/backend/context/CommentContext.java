package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.model.Comment;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentContext extends CommonContext {

    private List<Comment> comments;

    @Autowired
    private HttpRestClient httpRestClient;

    @Autowired
    private PostsContext postsContext;

    public void parseJsonResponseOfComments() throws JsonProcessingException {
        comments = mapFromJsonList(httpRestClient.getResponseBody().asString(), Comment.class);
    }

    public boolean checkEmailAddressFormat() {
        List<String> emailAddressList = getEmailAddress();
        for (String email : emailAddressList) {
            if (!checkEmailFormat(email)) {
                return false;
            }
        }
        return true;
    }

    public List<String> getEmailAddress() {
        return comments.stream().map(Comment::getEmail).collect(Collectors.toList());
    }

}
