package com.yeshwr.deadlock.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yeshwr.external.service.ExternalServiceImpl;

/**
 * @author yeshwr
 * @param <V>
 *
 */
@Service
public class Transfer {
	
	private static final Logger log = LoggerFactory.getLogger(ExternalServiceImpl.class);
	
	/**
	 * Transfer's money from one account to the other. Sample class for deadlock
	 * 
	 * @param from
	 * @param to
	 * @param amount
	 */
	public void transfer(Account from, Account to, double amount) {
		//Synchronized block 
		synchronized (from) {
			log.info("Acquiring lock1");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Synchronized block
			synchronized (to) {
				log.info("Acquiring lock2");
				from.withdraw(amount);
				to.deposit(amount);
			}
		}
	}
}
