package com.in28minutes.rest.webservices.restfullwebservices.user.posts;

public class Post {

    private Integer id;
    private Integer userId;
    private String body;

    protected Post() {

    }

    public Post(Integer id, Integer userId, String body) {
	super();
	this.id = id;
	this.userId = userId;
	this.body = body;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setUserId(Integer userId) {
	this.userId = userId;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    @Override
    public String toString() {
	return "Post [id=" + id + ", userId=" + userId + ", body=" + body + "]";
    }

}
