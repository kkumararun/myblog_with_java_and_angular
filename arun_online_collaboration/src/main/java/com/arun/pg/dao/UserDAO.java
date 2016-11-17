package com.arun.pg.dao;

import java.util.List;

import com.arun.pg.model.User;

public interface UserDAO {
	
	     
	    User findById(String email);      
	    void saveUser(User user);	     
	    void updateUser(User user);	     
	    void deleteUserById(String email);	 
	    List<User> findAllUsers();
	    void deleteAllUsers();
	    public boolean isUserExist(User user);
	
}
