package com.in28minutes.rest.webservices.restfullwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.in28minutes.rest.webservices.restfullwebservices.user.posts.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Details description of User")
@Entity
public class User {
	
	@Id
	@GeneratedValue
    private Integer id;
    
    @Size(min=3, message="User must have atleast 3 characters")
    @ApiModelProperty(notes="User must have atleast 3 characters")
    private String name;
    
    @Past
    @ApiModelProperty(notes="Birth date shuld be in the past")
    //For avoid to send back user birthday info
    //@JsonIgnore
    private Date birthday;
    
    @OneToMany(mappedBy="user")
    private List<Post> postList;

    protected User() {
	
    }
    
    public User(Integer id, String name, Date birthday) {
	super();
	this.id = id;
	this.name = name;
	this.birthday = birthday;
    }
    
    public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
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
