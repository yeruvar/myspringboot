package com.yeshwr.external.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.exception.ERRROR_CODE;
import com.yeshwr.exception.MyAppException;
import com.yeshwr.exception.ResourceNotFoundException;
import com.yeshwr.external.model.RequestedData;
import com.yeshwr.external.service.ExternalServiceImpl;

/**
 * Controller class to provide rest api's that queries external REST end point.
 * 
 * Provides api to the Problem 6
 * 
 * @author eruvaray
 */
@RestController()
@RequestMapping("/data")
public class ExternalWebService {

	@Autowired
	ExternalServiceImpl externalService;

	@GetMapping
	public ResponseEntity<?> searchAllData() {
		List<RequestedData> requestedData = null;
		try {
			requestedData = externalService.getData();
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (CoreApplicationException e) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
			return new ResponseEntity<MyAppException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<RequestedData>>(requestedData, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getData(@PathVariable int id) {
		RequestedData requestedData = new RequestedData();
		try {
			requestedData = externalService.getData(id);
		} catch (ResourceNotFoundException ex) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} catch (CoreApplicationException e) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
			return new ResponseEntity<MyAppException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<RequestedData>(requestedData, HttpStatus.OK);
	}
}
