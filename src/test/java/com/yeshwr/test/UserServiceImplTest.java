package com.yeshwr.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.user.model.User;
import com.yeshwr.user.repository.UserRepository;
import com.yeshwr.user.service.UserServiceImpl;

/**
 * Test class to test methods in UserServiceImpl 
 * 
 * @author yeshwr
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private User user;

	@Mock
	DataAccessException dataAccessException;

	@Before
	public void setUp() {

	}

	@Test
	public void testGetUsersSuccessfully() throws CoreApplicationException {
		List<User> expectedUsers = new ArrayList<>();
		User testUser = new User(45, "Ben", "ben.123@gmail.com");
		User aTestUser = new User(54, "Raj", "raj@gmail.com");
		expectedUsers.add(testUser);
		expectedUsers.add(aTestUser);
		Mockito.when(userRepository.findAll()).thenReturn(expectedUsers);

		List<User> result = userService.getUsers();
		verify(userRepository).findAll();

		assertThat(result).isEqualTo(expectedUsers);

	}

	@Test(expected = CoreApplicationException.class)
	public void testGetUsersFailure() throws CoreApplicationException {
		Mockito.doThrow(dataAccessException.getClass()).when(userRepository).findAll();
		userService.getUsers();
		verify(userRepository).findAll();
	}

	@Test
	public void testGetUserSuccessfully() throws CoreApplicationException {
		User testUser = new User(45, "Ben", "hello@gmail.com");
		Mockito.when(userRepository.findOne(testUser.getId())).thenReturn(testUser);
		int id = 45;
		User user = userService.getUser(id);
		verify(userRepository).findOne(id);
		assertThat(user.getId()).isEqualTo(id);
	}

	@Test
	public void testGetUserNotFound() throws CoreApplicationException {
		User testUser = new User(45, "Ben", "hello@gmail.com");
		Mockito.when(userRepository.findOne(testUser.getId())).thenReturn(testUser);
		int id = 5;
		User user = userService.getUser(id);
		verify(userRepository).findOne(id);
		assertThat(user).isNull();
	}

	@Test(expected = CoreApplicationException.class)
	public void testGetUserFailure() throws CoreApplicationException {
		User user = new User(45, null, "hello@gmail.com");
		int id = user.getId();
		Mockito.doThrow(dataAccessException.getClass()).when(userRepository).findOne(id);
		userService.getUser(id);
		verify(userRepository).findOne(id);
	}

	@Test
	public void testAddUserSuccessfully() throws CoreApplicationException {
		User expectedUser = new User(45, "Ben", "hello@gmail.com");
		Mockito.when(userRepository.save(expectedUser)).thenReturn(expectedUser);
		User addedUser = userService.addUser(expectedUser);
		verify(userRepository).save(expectedUser);
		assertThat(addedUser).isEqualTo(expectedUser);
	}

	@Test(expected = CoreApplicationException.class)
	public void testAddUserFailure() throws CoreApplicationException {
		User user = new User(45, null, "hello@gmail.com");
		Mockito.doThrow(dataAccessException.getClass()).when(userRepository).save(user);
		userService.addUser(user);
		verify(userRepository).save(user);
	}

	@Test
	public void testUpdateUserSuccessfully() throws CoreApplicationException {
		User expectedUser = new User(45, "Ben", "hello@gmail.com");
		Mockito.when(userRepository.save(expectedUser)).thenReturn(expectedUser);
		User addedUser = userService.updateUser(expectedUser);
		verify(userRepository).save(expectedUser);

		assertThat(addedUser).isEqualTo(expectedUser);
	}

	@Test(expected = CoreApplicationException.class)
	public void testUpdateUserFailure() throws CoreApplicationException {
		User user = new User(45, null, "hello@gmail.com");
		Mockito.doThrow(dataAccessException.getClass()).when(userRepository).save(user);
		userService.updateUser(user);
		verify(userRepository).save(user);
	}

	@Test
	public void testDeleteUserSuccessfully() throws CoreApplicationException {
		int id = 45;
		doNothing().when(userRepository).delete(id);
		userService.deleteUser(id);
		verify(userRepository).delete(id);

	}

	@Test(expected = CoreApplicationException.class)
	public void testDeleteUserFailure() throws CoreApplicationException {
		int id = 45;
		Mockito.doThrow(dataAccessException.getClass()).when(userRepository).delete(id);
		userService.deleteUser(id);
		verify(userRepository).delete(id);
	}

}
