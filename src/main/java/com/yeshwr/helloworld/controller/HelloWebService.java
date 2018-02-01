package com.yeshwr.helloworld.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeshwr.helloworld.model.Hello;

/**
 * Controller for Hello World api's
 * 
 * Api to the Problem 1. 
 * 
 * @author yeshwr
 *
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloWebService {

	private static final int id = 1;

	/**
	 * Hello World api
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> hello() {
		Hello hello = new Hello(id, "Hello World");
		return new ResponseEntity<Hello>(hello, HttpStatus.OK);
		
	}
}
