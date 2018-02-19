package com.in28minutes.rest.webservices.restfullwebservices.user.posts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoPostsFoundException extends RuntimeException {

    public NoPostsFoundException(String message) {
	super(message);
    }

}
