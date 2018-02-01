package com.yeshwr.test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.exception.ResourceNotFoundException;
import com.yeshwr.user.controller.UserWebService;
import com.yeshwr.user.model.User;
import com.yeshwr.user.service.UserServiceImpl;

/**
 * Test Class for methods in UserWebService
 * 
 * @author eruvaray
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserWebServiceTest {

	@InjectMocks
	private UserWebService userWebService;

	@Mock
	private UserServiceImpl userService;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userWebService).build();
	}

	@Test
	public void searchUsersSuccessfully() throws Exception {
		List<User> expectedUsers = new ArrayList<>();
		User testUser = new User(45, "Ben", "ben.123@gmail.com");
		User aTestUser = new User(54, "Raj", "raj@gmail.com");
		expectedUsers.add(testUser);
		expectedUsers.add(aTestUser);

		when(userService.getUsers()).thenReturn(expectedUsers);
		this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(userService).getUsers();
	}

	@Test
	public void searchUsersNotFound() throws Exception {
		when(userService.getUsers()).thenReturn(null);
		this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		verify(userService).getUsers();
	}

	@Test
	public void searchUserSuccessfully() throws Exception {
		User testUser = new User(10, "Ben", "ben.123@gmail.com");
		when(userService.getUser(testUser.getId())).thenReturn(testUser);
		this.mockMvc.perform(get("/users/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(userService).getUser(testUser.getId());
	}

	@Test
	public void searchUserNotFound() throws Exception {
		when(userService.getUser(10)).thenReturn(null);
		this.mockMvc.perform(get("/users/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		verify(userService).getUser(10);
	}

	@Test
	public void addUserSuccessfully() throws Exception {
		User testUser = new User(11, "Ben", "ben.123@gmail.com");
		when(userService.addUser(testUser)).thenReturn(testUser);
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(asJsonString(testUser)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void addUserBadRequest() throws Exception {
		User testUser = new User(11, null, "ben.123@gmail.com");
		this.mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(asJsonString(testUser)))
			.andExpect(status().isBadRequest());
		verify(userService, times(0)).addUser(testUser);
	}
	
	@Test
	public void updateUserSuccessfully() throws Exception {
		User updatedUser = new User(11, "Raj", "raj.123@gmail.com");
		when(userService.updateUser(updatedUser)).thenReturn(updatedUser);
		this.mockMvc.perform(put("/users/11").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatedUser)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void udpateUserBadRequest() throws Exception {
		User testUser = new User(11, null, "ben.123@gmail.com");
		this.mockMvc.perform(put("/users/11").contentType(MediaType.APPLICATION_JSON).content(asJsonString(testUser)))
			.andExpect(status().isBadRequest());
		verify(userService, times(0)).updateUser(testUser);
	}
	
	@Test
	public void deleteUserSuccessfully() throws Exception {
		User testUser = new User(10, "Ben", "ben.123@gmail.com");
		doNothing().when(userService).deleteUser(testUser.getId());
		this.mockMvc.perform(delete("/users/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(userService).deleteUser(testUser.getId());
	}

	@Test
	public void deleteUserNotFound() throws Exception {
		int id =10;
		doThrow(ResourceNotFoundException.class).when(userService).deleteUser(id);
		this.mockMvc.perform(delete("/users/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		verify(userService).deleteUser(10);
	}
	
	@Test
	public void deleteUserDatabaseException() throws Exception {
		int id =10;
		doThrow(CoreApplicationException.class).when(userService).deleteUser(id);
		this.mockMvc.perform(delete("/users/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
		verify(userService).deleteUser(10);
	}

	/**
	 * Helper method to convert java object to json
	 * 
	 * @param obj
	 * @return
	 */
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
