package com.in28minutes.rest.webservices.restfullwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details description of User")
public class User {

    private Integer id;
    
    @Size(min=3, message="User must have atleast 3 characters")
    @ApiModelProperty(notes="User must have atleast 3 characters")
    private String name;
    
    @Past
    @ApiModelProperty(notes="Birth date shuld be in the past")
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
