package com.arun.pg.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arun.pg.dao.UserDAO;
import com.arun.pg.model.User;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8887")
public class UserController {

	@Autowired
	UserDAO userDAO;

	
	
	
	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST)
	public ResponseEntity<User> loginUser(@PathVariable String username,@PathVariable String password) {

		User user = new User();
		user=userDAO.findById(username);
		System.out.println(user);		
		System.out.println(username);

		if (userDAO.isUserExist(user)) {
			
			
			if(user.getPassword().equals(password))
			{
				user.setPassword("");
				String role=user.getRole();
				return new ResponseEntity<User>(user,HttpStatus.OK);		
			}
		} else {

			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user,HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	@RequestMapping(value="/", method=RequestMethod.POST)
//public 	ResponseEntity loginSuccess() {
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	// -------------------Retrieve All
	// Users--------------------------------------------------------
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userDAO.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You
																			// many
																			// decide
																			// to
																			// return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// User--------------------------------------------------------

//	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
//		System.out.println("Fetching User with id " + id);
//		User user = userDAO.findById(id);
//		if (user == null) {
//			System.out.println("User with id " + id + " not found");
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}

	// -------------------Create a
	// User--------------------------------------------------------

	@RequestMapping(value = "/user/{username}/{name}/{password}", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@PathVariable String username, @PathVariable String name,
			@PathVariable String password, UriComponentsBuilder ucBuilder) {

		User user = new User();
		// user=userDAO.findById(username);
		// System.out.println(username);
		//
		// if (user != null) {
		// System.out.println("A User with name " + user.getUserName() + "
		// already exist");
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		// } else {

		user.setEnable(true);
		user.setName(name);
		user.setUserName(username);
		user.setRole("ROLE_USER");
		user.setPassword(password);
		System.out.println(user);

		if (userDAO.isUserExist(user)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {

			userDAO.saveUser(user);
		}
		// }

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// @RequestMapping(value = "/user", method = RequestMethod.POST)
	// public ResponseEntity<Void> createUser(@RequestBody User
	// user,UriComponentsBuilder ucBuilder) {
	//
	//
	// System.out.println("Creating User " + user.getUserName());
	// System.out.println("Creating User " + user.getUserName());
	// if (userDAO.isUserExist(user)) {
	// System.out.println("A User with name " + user.getUserName() + " already
	// exist");
	// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	// }
	//
	// userDAO.saveUser(user);
	//
	// HttpHeaders headers = new HttpHeaders();
	//// headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserName()).toUri());
	// return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	// }

	// ------------------- Update a User
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
		System.out.println("Updating User " + id);

		User currentUser = userDAO.findById(id);

		if (currentUser == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		// currentUser.setUsername(user.getUserName());
		currentUser.setName(user.getName());

		userDAO.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User
	// --------------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
		System.out.println("Fetching & Deleting User with id " + id);

		User user = userDAO.findById(id + ".com");
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userDAO.deleteUserById(id + ".com");
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	// ------------------- Delete All Users
	// --------------------------------------------------------
	//
	// @RequestMapping(value = "/user", method = RequestMethod.DELETE)
	// public ResponseEntity<User> deleteAllUsers() {
	// System.out.println("Deleting All Users");
	// userDAO.deleteAllUsers();
	// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	// }

}
