package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.challenge.backend.model.Post;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsContext {

    private static final String POSTS = "/posts";

    private List<Post> posts;
    private List<Integer> postIds;

    @Autowired
    private HttpRestClient httpRestClient;

    @Autowired
    private UserContext userContext;

    @Autowired
    private CommonContext commonContext;

    public void callGetPostsApi() {
       httpRestClient.sendHttpRequest(Method.GET, POSTS +"?userId="+userContext.getUserId());
       setPosts();
    }

    private void setPosts(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            posts = objectMapper.readValue(commonContext.getResponseBody().asString(), new TypeReference<List<Post>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public int getPostCount(){
        return posts.size();
    }

   public void gathersPostId(){
        postIds = posts.stream().map(Post::getId).collect(Collectors.toList());
   }

   public List<Integer> getPostIds(){
        return postIds;
   }

}
