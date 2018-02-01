package com.yeshwr.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.yeshwr.helloworld.controller.HelloWebService;

/**
 * Test class to test methods in HelloWebService 
 * 
 * @author yeshwr
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWebServiceTest {
	
	@InjectMocks
	private HelloWebService helloWebService;
	
	private MockMvc mockMvc;
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(helloWebService)
                .build();
    }
	
	@Test
	public void testHelloWorldApiSuccessfully() throws Exception {
		this.mockMvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}

}
