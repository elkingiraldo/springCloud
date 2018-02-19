package com.in28minutes.rest.webservices.restfullwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoServices {

    private static List<User> users = new ArrayList<>();
    
    private static int userCount = 3;
    
    static {
	users.add(new User(1, "Elkin", new Date()));
	users.add(new User(2, "Jairo", new Date()));
	users.add(new User(3, "Martha", new Date()));
    }
    
    public List<User> findAll(){
	return users;
    }
    
    public User findOne(int id) {
	for(User user : users) {
	    if (user.getId() == id) {
		return user;
	    }
	}
	return null;
    }
    
    public User save(User user) {
	if (user.getId() == null) {
	    user.setId(++userCount);
	}
	for (User userInDB : users) {
	    if (userInDB.getId() == user.getId()) {
		return null;
	    }
	}
	users.add(user);
	return user;
    }
    
    public User deleteById(int id) {
	Iterator<User> userIterator = users.iterator();
	while(userIterator.hasNext()) {
	    User user = userIterator.next();
	    if (user.getId() == id) {
		userIterator.remove();
		return user;
	    }
	}
	return null;
    }
    
}
