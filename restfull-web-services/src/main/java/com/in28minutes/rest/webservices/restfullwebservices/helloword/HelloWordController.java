package com.in28minutes.rest.webservices.restfullwebservices.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-word" )
    public String helloWord() {
	return "Hello Word";
    }
    
    @GetMapping(path = "/hello-word-bean" )
    public HelloWordBean helloWordBean() {
	return new HelloWordBean("Hello Word");
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/hello-word/path-variable/{name}" )
    public HelloWordBean helloWordPathVariable(@PathVariable String name) {
	return new HelloWordBean(String.format("Hello Word %s", name));
    }
    
}
