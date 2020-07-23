package com.mobiquity.challenge.backend.context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mobiquity.challenge.backend.model.Post;
import com.mobiquity.challenge.backend.restclient.HttpRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The PostContext class holds the functionality
 * specifically relating to the posts endpoint
 */

@Component
public class PostsContext extends CommonContext {

    private List<Post> posts;
    private List<Integer> postIds;
    private final HttpRestClient httpRestClient;

    @Autowired
    public PostsContext(HttpRestClient httpRestClient) {
        this.httpRestClient = httpRestClient;
    }

    /**
     * This method is used to parse JSON string receiving as an API response
     * and map it on the Post.class as List and saves it in the posts variable
     */
    public void parseJsonResponseOfPosts() throws JsonProcessingException {
        posts = mapFromJsonList(httpRestClient.getResponseBody().asString(), Post.class);
    }

    public int getPostCount() {
        return postIds.size();
    }

    /**
     * This method is used to fetch post ids from the list of post
     */
    public void gathersPostId() {
        postIds = posts.stream().map(Post::getId).collect(Collectors.toList());
    }

    public List<Integer> getPostIds() {
        return postIds;
    }

}
