package com.yeshwr.user.service;

import java.util.List;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.user.model.User;

/**
 * @author yeshwr
 *
 */
public interface UserService {
	/**
	 * @return
	 * @throws CoreApplicationException 
	 */
	public List<User> getUsers() throws CoreApplicationException;

	/**
	 * @param id
	 * @return
	 * @throws CoreApplicationException 
	 */
	public User getUser(Integer id) throws CoreApplicationException ;

	/**
	 * @param user
	 * @return
	 * @throws CoreApplicationException 
	 */
	public User addUser(User user) throws CoreApplicationException;

	/**
	 * @param user
	 * @return
	 * @throws CoreApplicationException 
	 */
	public User updateUser(User user) throws CoreApplicationException;

	/**
	 * @param id
	 * @return
	 * @throws CoreApplicationException 
	 */
	public void deleteUser(Integer id) throws CoreApplicationException;
}
