package com.in28minutes.rest.webservices.restfullwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.PostRepository;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = userRepository.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("User ID: " + id + ". Not found");
		}

		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {

		userRepository.delete(id);

	}

	// input - details of user
	// output - created && return created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		if (savedUser == null) {
			throw new UserAlreadyExistException("User ID: " + user.getId() + ". Already Exist!");
		}

		// created
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/jpa/users/{userId}/posts")
	public List<Post> retrieveAllPostsByUser(@PathVariable int userId) {

		// Validate user
		User user = userRepository.findOne(userId);
		if (user == null) {
			throw new UserNotFoundException("User ID: " + userId + ". Not Found");
		}

		// validate posts for user
		List<Post> postsList = user.getPostList();
		if (postsList.size() == 0) {
			throw new NoPostsFoundException("No posts for User ID: " + userId);
		}

		return postsList;

	}

	@PostMapping("/jpa/users/{userId}/posts")
	public ResponseEntity<Object> createPostForUser(@RequestBody Post post, @PathVariable int userId) {

		User user = userRepository.findOne(userId);

		if (user == null) {
			throw new UserNotFoundException("User ID: " + userId + ". Not Found");
		}

		post.setUser(user);

		postRepository.save(post);

		// created
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

}
