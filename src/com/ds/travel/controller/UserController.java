package com.ds.travel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.travel.dao.UserDAO;
import com.ds.travel.model.User;
import com.ds.travel.security.CustomAuthenticationProvider;


@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
		
		 //------------------- Retrieve All User --------------------------------------------------------
		 @RequestMapping(value = "/api/user/", method = RequestMethod.GET)
		    public ResponseEntity<List<User>> listAllUsers() {
			 	Authentication a = SecurityContextHolder.getContext().getAuthentication();
			 	System.out.println(a.getName());
			 	System.out.println(a.getPrincipal().toString());
			 	
		        List<User> users = userDAO.getAll();
		        if(users.isEmpty()){
		            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		        }
		        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		    }
		  
	    //-------------------Retrieve Single User--------------------------------------------------------
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
	        System.out.println("Fetching User with id " + id);
	    	Authentication a = SecurityContextHolder.getContext().getAuthentication();
		 	System.out.println(a.getName());
	        User user = userDAO.getById(id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	  
	    //-------------------Create a User--------------------------------------------------------
	    @RequestMapping(value = "/user/", method = RequestMethod.POST,  consumes = {MediaType.APPLICATION_JSON_VALUE})
	    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating User " + user.getUsername());
	        if (userDAO.isUserExist(user)) {
	            System.out.println("A User with name " + user.getUsername() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	        userDAO.add(user);
	        return new ResponseEntity<Void>(HttpStatus.CREATED);
	    }
	      
	    //------------------- Update a User --------------------------------------------------------
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
	        System.out.println("Updating User " + id);
	        User currentUser = userDAO.getById(id);
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        currentUser.setUsername(user.getUsername());
	        currentUser.setEmail(user.getEmail());
	        currentUser.setPassword(user.getPassword());
	        userDAO.update(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }
	  
}
