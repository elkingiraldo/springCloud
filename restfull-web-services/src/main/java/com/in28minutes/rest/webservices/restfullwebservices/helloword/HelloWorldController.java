package com.in28minutes.rest.webservices.restfullwebservices.helloword;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world" )
    public String helloWord() {
	return "Hello Word";
    }
    
    @GetMapping(path = "/hello-world-bean" )
    public HelloWordBean helloWordBean() {
	return new HelloWordBean("Hello Word");
    }
    
    @RequestMapping(method = RequestMethod.GET, path = "/hello-world/path-variable/{name}" )
    public HelloWordBean helloWordPathVariable(@PathVariable String name) {
	return new HelloWordBean(String.format("Hello Word %s", name));
    }
    
    @GetMapping(path = "/hello-world-internationalized" )
    public String helloWordInternationalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
	return messageSource.getMessage("good.morning.message", null, locale);
    }
    
}
