package com.yeshwr.text.service;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.yeshwr.exception.CoreApplicationException;

/**
 * @author yeshwr
 *
 */
@Service("textService")
public class TextServiceImpl implements TextService {

	// Declaring the pattern as constant as it can be reused
	private static final Pattern PATTERN = Pattern.compile(" ");

	/* (non-Javadoc)
	 * @see com.yeshwr.unique.TextProcessor#uniqueWordCount(java.lang.String)
	 */
	public Map<String, Integer> getUniqueWordCount(String text) throws CoreApplicationException {

		if(text== null) {
			throw new CoreApplicationException("The input text is null");
		}
		text = text.toLowerCase();
		String[] strArray = PATTERN.split(text);
		Map<String, Integer> words = new TreeMap<>();

		for (String str : strArray) {
			if ((str.length() > 0) && words.containsKey(str)) {
				words.put(str, words.get(str) + 1);
			} else if (str.length() > 0) {
				words.put(str, 1);
			}
		}
		return words;
	}

}
