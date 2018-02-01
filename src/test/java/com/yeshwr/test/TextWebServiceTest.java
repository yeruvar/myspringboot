package com.yeshwr.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeshwr.text.controller.TextWebService;
import com.yeshwr.text.model.InputText;
import com.yeshwr.text.service.TextService;

/**
 * Test class to test methods in TextWebService 
 * 
 * @author yeshwr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TextWebServiceTest {
	
	@InjectMocks
	private TextWebService textWebService;
	
	@Mock
	TextService textService;
	
	private MockMvc mockMvc;
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(textWebService)
                .build();
    }
	
	@Test
	public void getUniqueWordCountSuccessfully() throws Exception {
		
		Map<String, Integer> expectedResult = new TreeMap<>();
		expectedResult.put("has", 1);
		expectedResult.put("is", 1);
		expectedResult.put("repeated", 1);
		expectedResult.put("text", 1);
		expectedResult.put("this", 1);
		expectedResult.put("words", 1);

		InputText text = new InputText(1, "This text is has zero repeated words");
		Mockito.when(textService.getUniqueWordCount(text.getText())).thenReturn(expectedResult);
		this.mockMvc.perform(post("/words").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(text))).andExpect(status().isOk());
		verify(textService).getUniqueWordCount(text.getText());
	}
	
	@Test
	public void getUniqueWordCounBadRequest() throws Exception {
		InputText text = new InputText(1, null);
		this.mockMvc.perform(post("/words").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(text))).andExpect(status().isBadRequest());
		verify(textService, times(0)).getUniqueWordCount(text.getText());
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
