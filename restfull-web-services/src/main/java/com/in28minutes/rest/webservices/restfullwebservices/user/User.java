package com.in28minutes.rest.webservices.restfullwebservices.user;

import java.util.Date;
import java.util.List;

import com.in28minutes.rest.webservices.restfullwebservices.user.posts.Post;

public class User {

    private Integer id;
    private String name;
    private Date birthday;

    protected User() {
	
    }
    
    public User(Integer id, String name, Date birthday) {
	super();
	this.id = id;
	this.name = name;
	this.birthday = birthday;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Date getBirthday() {
	return birthday;
    }

    public void setBirthday(Date birthday) {
	this.birthday = birthday;
    }
    
    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
    }

}
