package com.yeshwr.text.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.exception.ERRROR_CODE;
import com.yeshwr.exception.MyAppException;
import com.yeshwr.text.model.InputText;
import com.yeshwr.text.model.WordCount;
import com.yeshwr.text.service.TextService;

/**
 * Controller class to provide rest api to process text and reutrn the unique words with the respective count
 * 
 * APi for the Problem 2. 
 * 
 * @author yeshwr
 *
 */
@RestController
public class TextWebService {

	@Autowired
	private TextService textProcessor;

	/**
	 * API to return the unique words along with the count for the input text
	 * 
	 * @param text
	 * @param bindingResult
	 * @return
	 * 		 The array of Object with word and count
	 */
	@PostMapping("/words")
	public ResponseEntity<Object> getUniqueWordCount(@Valid @RequestBody InputText text, BindingResult bindingResult) {

		// Validating the non null field id in the input
		if (bindingResult.hasErrors()) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INVALID_INPUT.getCode(),
					"The input is invalid. Please verify");
			return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
		}

		Map<String, Integer> words = null;
		List<WordCount> wordCount = null;
		try {
			words = textProcessor.getUniqueWordCount(text.getText());
			wordCount = this.convertToObjectList(words);
		} catch (CoreApplicationException cae) {
			MyAppException exception = new MyAppException(ERRROR_CODE.INVALID_INPUT.getCode(), cae.getMessage());
			return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(wordCount, HttpStatus.OK);
	}
	
	/**
	 * Convert the map of words to array of WordCount object
	 * 
	 * @param words
	 * @return
	 */
	private List<WordCount> convertToObjectList(Map<String, Integer> words) {
		List<WordCount> wordCount = new ArrayList<>();
		words.forEach((k,v) -> wordCount.add(new WordCount(k, v)));
		return wordCount;
	}
}
