package com.yeshwr.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.text.model.InputText;
import com.yeshwr.text.service.TextServiceImpl;


/**
 * Test class to test methods in TextServiceImpl
 * 
 * @author eruvaray
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TextServiceImplTest {
	
	@InjectMocks
	private TextServiceImpl textService;
	
	@Test
	public void getUniqueWordCountSucessfully() throws Exception {
		
		Map<String, Integer> expectedResult = new TreeMap<>();
		expectedResult.put("has", 1);
		expectedResult.put("repeated", 1);
		expectedResult.put("text", 1);
		expectedResult.put("this", 1);
		expectedResult.put("words", 1);
		expectedResult.put("zero", 1);

		InputText text = new InputText(1, "This text has zero repeated words");
		Map<String, Integer> result = textService.getUniqueWordCount(text.getText());
		assertThat(result).isEqualTo(expectedResult);
	}
	
	@Test(expected = CoreApplicationException.class)
	public void testGetUsersFailure() throws CoreApplicationException {
		textService.getUniqueWordCount(null);
	}

}
