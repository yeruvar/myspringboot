package com.yeshwr.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.exception.ResourceNotFoundException;
import com.yeshwr.user.model.User;
import com.yeshwr.user.repository.UserRepository;

/**
 * 
 * Service class to search, add, remove, update user
 * 
 * @author yeshwr
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	// Retrieve all rows from table and populate list with objects
	public List<User> getUsers() throws CoreApplicationException {
		List<User> users = new ArrayList<>();
		try {
			userRepository.findAll().forEach(users::add);
		} catch (DataAccessException ex) {
			throw new CoreApplicationException(ex);
		}
		return users;
	}

	// Retrieves one row from table based on given id
	public User getUser(Integer id) throws CoreApplicationException {
		User user = null;
		try {
			user = userRepository.findOne(id);
		} catch (DataAccessException ex) {
			throw new CoreApplicationException(ex);
		}
		return user;
	}

	// Inserts row into table
	public User addUser(User user) throws CoreApplicationException {
		User userAdded = null;
		try {
			userAdded = userRepository.save(user);
		} catch (DataAccessException ex) {
			throw new CoreApplicationException(ex);
		}
		return userAdded;
	}

	// Updates row in table
	public User updateUser(User user) throws CoreApplicationException {
		User userAdded = null;
		try {
			userAdded = userRepository.save(user);
		} catch (DataAccessException ex) {
			throw new CoreApplicationException(ex);
		}
		return userAdded;
	}

	// Removes row from table
	public void deleteUser(Integer id) throws CoreApplicationException {
		try {
			userRepository.delete(id);
		} 
		catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException(ex);
		}
		catch (DataAccessException ex) {
			throw new CoreApplicationException(ex);
		}
	}

}
