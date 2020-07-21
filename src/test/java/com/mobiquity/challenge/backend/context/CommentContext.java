package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.challenge.backend.model.Comment;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.restassured.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentContext {

    private static final String COMMENTS = "/comments";

    private List<Comment> comments;

    @Autowired
    private HttpRestClient httpRestClient;

    @Autowired
    private PostsContext postsContext;

    @Autowired
    private CommonContext commonContext;

    public void gatherCommentsData(){
        for(int id:postsContext.getPostIds()){
            callGetCommentApi(id);
            setComments();
        }
    }

    private void setComments(){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            comments = objectMapper.readValue(commonContext.getResponseBody().asString(), new TypeReference<List<Comment>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public boolean checkEmailAddressFormat(){
        List<String> emailAddressList = getEmailAddress();
        for(String email: emailAddressList){
            if(!commonContext.checkEmailFormat(email)){
                return false;
            }
        }
        return true;
    }


    public List<String> getEmailAddress(){
      return comments.stream().map(Comment::getEmail).collect(Collectors.toList());
    }


    public void callGetCommentApi(int postId){
        httpRestClient.sendHttpRequest(Method.GET, COMMENTS +"?postId="+postId);
    }
}
