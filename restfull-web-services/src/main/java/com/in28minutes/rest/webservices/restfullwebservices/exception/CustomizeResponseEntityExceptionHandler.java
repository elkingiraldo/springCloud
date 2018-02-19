package com.in28minutes.rest.webservices.restfullwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfullwebservices.user.UserAlreadyExistException;
import com.in28minutes.rest.webservices.restfullwebservices.user.UserInconsistentException;
import com.in28minutes.rest.webservices.restfullwebservices.user.UserNotFoundException;
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.NoPostsFoundException;
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.PostAlreadyExistException;
import com.in28minutes.rest.webservices.restfullwebservices.user.posts.PostNotFoundException;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {

	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoPostsFoundException.class)
    public final ResponseEntity<Object> handleNoPostsFoundException(NoPostsFoundException ex, WebRequest request) {

	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity(exceptionResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public final ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex, WebRequest request) {

	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex,
	    WebRequest request) {

	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PostAlreadyExistException.class)
    public final ResponseEntity<Object> handlePostAlreadyExistException(PostAlreadyExistException ex,
	    WebRequest request) {

	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(UserInconsistentException.class)
    public final ResponseEntity<Object> handleUserInconsistentException(UserInconsistentException ex,
	    WebRequest request) {

	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }

}