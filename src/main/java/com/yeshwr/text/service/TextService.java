package com.yeshwr.text.service;

import java.util.Map;

import com.yeshwr.exception.CoreApplicationException;

/**
 * @author yeshwr
 *
 */
public interface TextService {

	/**
	 * For a given String, returns the count of unique words. 
	 * 
	 * @param text
	 * @return
	 * 		A map of words with the count
	 * @throws CoreApplicationException 
	 */
	public Map<String, Integer> getUniqueWordCount(String text) throws CoreApplicationException;
}
