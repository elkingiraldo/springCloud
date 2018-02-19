package com.in28minutes.rest.webservices.restfullwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserInconsistentException extends RuntimeException {

    public UserInconsistentException(String message) {
	super(message);
    }

}
