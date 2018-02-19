package com.in28minutes.rest.webservices.restfullwebservices.user.posts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PostAlreadyExistException extends RuntimeException {

    public PostAlreadyExistException(String message) {
	super(message);
    }

}
