package com.yeshwr.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.exception.ERRROR_CODE;
import com.yeshwr.exception.MyAppException;
import com.yeshwr.exception.ResourceNotFoundException;
import com.yeshwr.user.model.User;
import com.yeshwr.user.service.UserServiceImpl;

/**
 * Controller class to provide rest api to add, delete, query rows in a database. Rest Api's for Problem 5
 * 
 * @author yeshwr
 *
 */
@RestController()
@RequestMapping("/users")
public class UserWebService {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping()
	public ResponseEntity<?> searchUsers() {
		List<User> users;
		try {
			users = userService.getUsers();
		} catch (CoreApplicationException e) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
			return new ResponseEntity<MyAppException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (users == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) {
		User user = null;
		try {
			user = userService.getUser(id);
		} catch (CoreApplicationException e) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
			return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		// Validating the fields in the input
		if (bindingResult.hasErrors()) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INVALID_INPUT.getCode(),
					"The input is invalid. Please verify");
			return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
		}

		User userAdded;
		try {
			userAdded = userService.addUser(user);
		} catch (CoreApplicationException e) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
			return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(userAdded, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		// Validating the fields in the input
		if (bindingResult.hasErrors()) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INVALID_INPUT.getCode(),
					"The input is invalid. Please verify");
			return new ResponseEntity<MyAppException>(exception, HttpStatus.BAD_REQUEST);
		}
		User userAdded;

		try {
			userAdded = userService.updateUser(user);
		} catch (CoreApplicationException e) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
			return new ResponseEntity<MyAppException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(userAdded, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		try {
			userService.deleteUser(id);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		catch (CoreApplicationException e) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
			return new ResponseEntity<MyAppException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
