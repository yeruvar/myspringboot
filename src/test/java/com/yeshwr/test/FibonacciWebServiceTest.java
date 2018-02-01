package com.yeshwr.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.yeshwr.fibonacci.controller.FibonacciWebService;
import com.yeshwr.fibonacci.service.FibonacciService;

/**
 * Test class to test methods in FibonacciWebService
 * 
 * @author yeshwr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FibonacciWebServiceTest {

	@InjectMocks
	private FibonacciWebService fibonacciWebService;

	@Mock
	private FibonacciService fibonacciService;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(fibonacciWebService).build();
	}

	@Test
	public void testFibonacciApiSuccessfully() throws Exception {
		this.mockMvc.perform(get("/fibonacci/3").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(fibonacciService, times(1)).fetchFibonacci(1);
		verify(fibonacciService, times(1)).fetchFibonacci(2);
		verify(fibonacciService, times(1)).fetchFibonacci(3);
	}

	@Test
	public void testFibonacciApiFailure() throws Exception {
		this.mockMvc.perform(get("/fibonacci/-1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

}
