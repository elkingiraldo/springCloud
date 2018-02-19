package com.in28minutes.rest.webservices.restfullwebservices.helloword;

public class HelloWordBean {

    private Object message;

    public HelloWordBean(String message) {
	this.message = message;
    }

    public void setMessage(Object message) {
	this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public String toString() {
	return "HelloWordBean [message=" + message + "]";
    }

}
