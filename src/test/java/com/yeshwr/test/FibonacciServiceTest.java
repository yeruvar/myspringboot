package com.yeshwr.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yeshwr.fibonacci.service.FibonacciService;

/**
 * Test class for FibonacciService
 * 
 * @author eruvaray
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FibonacciServiceTest {
	
	@InjectMocks
	private FibonacciService fibonacciService;
	
	@Test
	public void testGetFibonacciSuccessfully() {
		long expected = 5;
		long result = fibonacciService.fetchFibonacci(5);
		assertThat(result).isEqualTo(expected);
	}

}
