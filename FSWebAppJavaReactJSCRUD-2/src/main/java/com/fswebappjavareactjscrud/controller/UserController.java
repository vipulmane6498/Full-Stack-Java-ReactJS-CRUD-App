package com.fswebappjavareactjscrud.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fswebappjavareactjscrud.dao.UserDao;
import com.fswebappjavareactjscrud.exception.UserNotFoundException;
import com.fswebappjavareactjscrud.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //@CrossOrigin => to connect frontend to backend
public class UserController {

	@Autowired
	UserDao userDao;
	
	@PostMapping("/user")
	ResponseEntity<User> newUser(@RequestBody User newUser) {
		User savedUser = userDao.save(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@GetMapping("/getAllUsers")
	ResponseEntity<List<User>> getAllusers(){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDao.findAll());
	}

//	@GetMapping("/getUser")
//	ResponseEntity<Optional<User>> getUserByID(long id){
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDao.findById(id).orElseThrow(()->new UserNotFoundException(id)));
//	}
	
	@GetMapping("/getUser/id/{id}")
	User getUserByID(@PathVariable long id){
		return userDao.findById(id).orElseThrow(()->new UserNotFoundException(id));
	}
	@DeleteMapping("/deleteUser/id/{id}")
	String removeUser(@PathVariable long id) {
		if(!userDao.existsById(id)){
			throw new UserNotFoundException(id);
		}
		userDao.deleteById(id);    
		return "User with id: "+id+" has been succefully deleted";
		}
	
	@PutMapping("/editUser/id/{id}")
	ResponseEntity<User> editUser(@RequestBody User user, @PathVariable long id){
		User foundUser = userDao.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		foundUser.setUserName(user.getUserName());
		foundUser.setEmail(user.getEmail());
		foundUser.setMobNumber(user.getMobNumber());
		
		User updatedUser = userDao.save(foundUser);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);
	}
}




















