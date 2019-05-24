package com.mainul35.authServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mainul35.authServer.model.User;
import com.mainul35.authServer.service.UserService;

@RestController
@RequestMapping("/apiv2")
	public class Apiv2Controller {
	  
	    @Autowired
	    UserService userService;  //Service which will do all data retrieval/manipulation work
	  
	      
	    //-------------------Retrieve All Users--------------------------------------------------------
	      
	    @RequestMapping(value = "/user/", method = RequestMethod.GET)
	    public ResponseEntity<List<User>> listAllUsers() {
	        List<User> users = userService.findAllUsers();
	        System.out.println("APIv2 executing...");
			
	        if(users.isEmpty()){
	            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	    }
	  
	  
	    //-------------------Retrieve Single User--------------------------------------------------------
	      
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
	        System.out.println("Fetching User with id " + id);
	        User user = userService.findById(id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	  
	      
	      
	    //-------------------Create a User--------------------------------------------------------
	      
	    @RequestMapping(value = "/user/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating User " + user.getName());
	  
	        if (userService.isUserExist(user)) {
	            System.out.println("A User with name " + user.getName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	  
	        userService.saveUser(user);
	  
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	  
	      
	    //------------------- Update a User --------------------------------------------------------
	      
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
	        System.out.println("Updating User " + id);
	          
	        User currentUser = userService.findById(id);
	          
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	  
	        currentUser.setName(user.getName());
	        currentUser.setAge(user.getAge());
	        currentUser.setSalary(user.getSalary());
	          
	        userService.updateUser(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }
	  
	    //------------------- Delete a User --------------------------------------------------------
	      
	    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	  
	        User user = userService.findById(id);
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	  
	        userService.deleteUserById(id);
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
	  
	      
	    //------------------- Delete All Users --------------------------------------------------------
	      
	    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	    public ResponseEntity<User> deleteAllUsers() {
	        System.out.println("Deleting All Users");
	  
	        userService.deleteAllUsers();
	        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	    }
	    
		
		@GetMapping("/users")
		public String getAllUsers(){
			System.out.println("APIv2 executing...");
			return "APIv2 executing...";
		}
		
		@RequestMapping(value = "/",method=RequestMethod.GET)
		@ResponseBody public ResponseEntity<List<User>> getApis(){
			System.out.println("APIv2 executing...");
			return new ResponseEntity<List<User>>(userService.findAllUsers(), HttpStatus.OK);
		}
}
