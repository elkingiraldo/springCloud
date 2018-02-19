package com.in28minutes.rest.webservices.restfullwebservices.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.in28minutes.rest.webservices.restfullwebservices.user.posts.NoPostsFoundException;
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.Post;
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.PostAlreadyExistException;
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.PostDaoService;
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.PostNotFoundException;

@RestController
public class UserController {

    @Autowired
    private UserDaoServices userDaoServices;

    @Autowired
    private PostDaoService postDaoServices;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
	return userDaoServices.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
	User user = userDaoServices.findOne(id);

	if (user == null) {
	    throw new UserNotFoundException("User ID: " + id + ". Not found");
	}

	return user;
    }

    // input - details of user
    // output - created && return created URI 
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
	User savedUser = userDaoServices.save(user);

	if (savedUser == null) {
	    throw new UserAlreadyExistException("User ID: " + user.getId() + ". Already Exist!");
	}

	// created
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
		.toUri();
	return ResponseEntity.created(location).build();
	//
    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int userId) {

	// Validate user
	User user = userDaoServices.findOne(userId);
	if (user == null) {
	    throw new UserNotFoundException("User ID: " + userId + ". Not Found");
	}

	// validate posts for user
	List<Post> postsList = postDaoServices.findAllPostsByUser(userId);
	if (postsList.size() == 0) {
	    throw new NoPostsFoundException("No posts for User ID: " + userId);
	}

	return postsList;

    }

    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/posts/{postId}")
    public Post retrievePostByUser(@PathVariable int userId, @PathVariable int postId) {

	// Validate user
	User user = userDaoServices.findOne(userId);
	if (user == null) {
	    throw new UserNotFoundException("User ID: " + userId + ". Not found");
	}

	// validate posts for user
	Post post = postDaoServices.findPostByUser(userId, postId);
	if (post == null) {
	    throw new PostNotFoundException("Post ID: " + postId + ". Not Found");
	}

	return post;
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Object> createPostForUser(@RequestBody Post post, @PathVariable int userId) {

	if (post.getUserId() == null) {
	    post.setUserId(userId);
	}

	if (userId != post.getUserId()) {
	    throw new UserInconsistentException("User ID in URL:" + userId + " and " + "User ID in Post: "
		    + post.getUserId() + " should be the same!");
	}

	List<User> usersInDB = userDaoServices.findAll();
	List<Integer> userIds = new ArrayList<Integer>();

	for (User user : usersInDB) {
	    userIds.add(user.getId());
	}

	if (post.getUserId() == null || !userIds.contains(post.getUserId())) {
	    throw new UserNotFoundException("User ID: " + post.getUserId() + ". Not found");
	}

	Post savedPost = postDaoServices.save(post);

	if (savedPost == null) {
	    throw new PostAlreadyExistException("Post ID: " + post.getId() + ". Already Exist!");
	}

	// created
	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
		.toUri();
	return ResponseEntity.created(location).build();
	//
    }

}
