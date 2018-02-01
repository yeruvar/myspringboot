package com.yeshwr.deadlock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yeshwr.deadlock.model.Account;
import com.yeshwr.deadlock.model.Transfer;
import com.yeshwr.exception.ERRROR_CODE;
import com.yeshwr.exception.MyAppException;

/**
 * Controller class to provide rest api which creates deadlock
 * 
 * REST api for the problem 4. 
 * 
 * @author yeshwr
 *
 */
@RestController
public class DeadLockWebService {

	private final Account from = new Account("A", 1000);
	private final Account to = new Account("B", 300);

	private final long timeoutMillis = 10000;

	@Autowired
	Transfer transfer;

	/**
	 * REST api to find initiate transfer which leads to deadlock
	 * 
	 * @return
	 */
	@GetMapping("/transfer")
	public ResponseEntity<Object> createDeadLock() {

		// Create two threads which try to acquire locks on the same objects in the
		// reverse order
		Runnable task1 = () -> {
			transfer.transfer(from, to, 100);
		};

		Runnable task2 = () -> {
			transfer.transfer(to, from, 100);
		};

		Thread thread1 = new Thread(task1);
		thread1.start();
		Thread thread2 = new Thread(task2);
		thread2.start();

		// Check if the deadlock has occured. If yes, return an exception
		boolean isDeadlock = detectDeadlock(thread1, thread2, timeoutMillis);
		if (isDeadlock) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INTERNAL_SERVER_ERROR.getCode(), "Deadlock has occured") ;
			return new ResponseEntity<Object>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * Checking if the two threads are in deadlock. If the two threads are alive for
	 * more than 10 seconds, they are in dead lock situation
	 * 
	 * @param thread1
	 * @param thread2
	 * @param milliSeconds
	 * @return
	 */
	private boolean detectDeadlock(Thread thread1, Thread thread2, long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (thread1.isAlive() && thread2.isAlive()) {
			return true;
		}
		return false;
	}

}
