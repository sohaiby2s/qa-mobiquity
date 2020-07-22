package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.model.Post;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsContext extends CommonContext {

    private List<Post> posts;
    private List<Integer> postIds;

    @Autowired
    private HttpRestClient httpRestClient;

    @Autowired
    private UserContext userContext;


    public void parseJsonResponseOfPosts() throws JsonProcessingException {
        posts = mapFromJsonList(httpRestClient.getResponseBody().asString(), Post.class);
    }

    public int getPostCount() {
        return postIds.size();
    }

    public void gathersPostId() {
        postIds = posts.stream().map(Post::getId).collect(Collectors.toList());
    }

    public List<Integer> getPostIds() {
        return postIds;
    }

}
