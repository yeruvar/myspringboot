package com.yeshwr.fibonacci.service;

import org.springframework.stereotype.Service;

/**
 * @author yeshwr
 *
 */
@Service
public class FibonacciService {

	/**
	 * Returns the fibonacci value  for the given number
	 * 
	 * @param n
	 * @return
	 */
	public long fetchFibonacci(int n) {
		if (n <= 1)
			return n;
		else
			return fetchFibonacci(n - 1) + fetchFibonacci(n - 2);
	}
}
