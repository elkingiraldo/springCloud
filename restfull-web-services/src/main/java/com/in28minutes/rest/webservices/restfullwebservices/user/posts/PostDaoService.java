package com.in28minutes.rest.webservices.restfullwebservices.user.posts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PostDaoService {
    
    private static List<Post> posts = new ArrayList<>();
    
    private static int postCount = 4;
    
    static {
//	posts.add(new Post(1,1,"Post de elkin 1"));
//	posts.add(new Post(2,2,"Post de Jairo 1"));
//	posts.add(new Post(3,2,"Post de Jairo 2"));
//	posts.add(new Post(4,2,"Post de Jairo 3"));
    }
    
    public List<Post> findAllPostsByUser(int userId){
	List<Post> userPosts = new ArrayList<Post>();
	
//	for(Post post : posts) {
//	    if(post.getUserId() == userId) {
//		userPosts.add(post);
//	    }
//	}
	
	return userPosts;
	
    }
    
    public Post findPostByUser(int userId, int postId){
//	for (Post post : posts) {
//	    if (post.getUserId() == userId && post.getId() == postId) {
//		return post;
//	    }
//	}
	
	return null;
    }

    public Post save(Post post) {
	
	if (post.getId() == null) {
	    post.setId(++postCount);
	}
	
	for (Post postInDB : posts) {
	    if (postInDB.getId() == post.getId()) {
		return null;
	    }
	}
	posts.add(post);
	return post;
    }
    
}
