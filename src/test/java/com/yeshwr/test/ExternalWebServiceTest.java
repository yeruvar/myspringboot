package com.yeshwr.test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.exception.ResourceNotFoundException;
import com.yeshwr.external.controller.ExternalWebService;
import com.yeshwr.external.model.RequestedData;
import com.yeshwr.external.service.ExternalServiceImpl;

/**
 * Test Class for methods in ExternalWebService
 * 
 * @author eruvaray
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalWebServiceTest {

	@InjectMocks
	private ExternalWebService externalWebService;

	@Mock
	private ExternalServiceImpl externalService;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(externalWebService).build();
	}

	@Test
	public void searchDataSuccessfully() throws Exception {
		List<RequestedData> requestedData = new ArrayList<>();
		RequestedData aRequestedData1 = new RequestedData(1, 1, "Hello", "My First Comment");
		RequestedData aRequestedData2 = new RequestedData(1, 2, "Hello", "My Second Comment");
		requestedData.add(aRequestedData1);
		requestedData.add(aRequestedData2);

		when(externalService.getData()).thenReturn(requestedData);
		this.mockMvc.perform(get("/data").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(externalService).getData();
	}

	@Test
	public void searchDataForIdSuccessfully() throws Exception {
		RequestedData aRequestedData = new RequestedData(1, 1, "Hello", "My First Comment");
		when(externalService.getData(1)).thenReturn(aRequestedData);
		this.mockMvc.perform(get("/data/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(externalService).getData(1);
	}
	
	@Test
	public void searchDataNotFound() throws Exception {
		int id =10;
		doThrow(ResourceNotFoundException.class).when(externalService).getData(id);
		this.mockMvc.perform(get("/data/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		verify(externalService).getData(id);
	}
	
	@Test
	public void searchDataForIdNotFound() throws Exception {
		doThrow(ResourceNotFoundException.class).when(externalService).getData();
		this.mockMvc.perform(get("/data").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
		verify(externalService).getData();
	}
	
	@Test
	public void searchDataRestError() throws Exception {
		int id =10;
		doThrow(CoreApplicationException.class).when(externalService).getData(id);
		this.mockMvc.perform(get("/data/10").accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
		verify(externalService).getData(id);
	}
	
	@Test
	public void searchDataForIdRestError() throws Exception {
		doThrow(CoreApplicationException.class).when(externalService).getData();
		this.mockMvc.perform(get("/data").accept(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());
		verify(externalService).getData();
	}
}
