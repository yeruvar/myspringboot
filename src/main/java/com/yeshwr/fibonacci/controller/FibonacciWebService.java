package com.yeshwr.fibonacci.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yeshwr.exception.ERRROR_CODE;
import com.yeshwr.exception.MyAppException;
import com.yeshwr.fibonacci.model.Fibonacci;
import com.yeshwr.fibonacci.service.FibonacciService;

/**
 * Controller to provide api for finding the fibonacci series. 
 * 
 * Api to the Problem 3.  
 * 
 * @author yeshwr
 *
 */
@RestController
public class FibonacciWebService {

	@Autowired
	FibonacciService fibonacciService;

	/**
	 * API to return the fibonacci array for given input value
	 * 
	 * @param text
	 * @param bindingResult
	 * @return
	 */
	@GetMapping("/fibonacci/{number}")
	public ResponseEntity<?> getFibonacci(@PathVariable int number) {
		
		if(number<0) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INVALID_INPUT.getCode(),
					"The input is invalid. Please pass the positive number");
			return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
		}

		List<Fibonacci> fibonacciSeries = new ArrayList<>();

		for (int i = 1; i <= number; i++) {
			long fibonacciValue = fibonacciService.fetchFibonacci(i);
			fibonacciSeries.add(new Fibonacci(i, fibonacciValue));
		}
		return new ResponseEntity<List<Fibonacci>>(fibonacciSeries, HttpStatus.OK);
	}
}
