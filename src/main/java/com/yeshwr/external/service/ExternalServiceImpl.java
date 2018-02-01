package com.yeshwr.external.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.exception.ResourceNotFoundException;
import com.yeshwr.external.model.RequestedData;

@Service
public class ExternalServiceImpl implements ExternalService {

	private static final Logger log = LoggerFactory.getLogger(ExternalServiceImpl.class);
	
	@Autowired
	RestTemplate restTemplate ;

	@Value("${external.uri}")
	private String externalUrl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yeshwr.external.service.ExternalService#getData()
	 */
	public List<RequestedData> getData() throws CoreApplicationException {
		List<RequestedData> requestedData = new ArrayList<>();
		ParameterizedTypeReference<List<RequestedData>> data = new ParameterizedTypeReference<List<RequestedData>>() {
		};
		try {
			log.info("Calling the external url: "+externalUrl);
			ResponseEntity<List<RequestedData>> response = restTemplate.exchange(externalUrl, HttpMethod.GET, null,
					data);
			requestedData = response.getBody();
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new ResourceNotFoundException(ex);
			} else {
				throw new CoreApplicationException("Error Occured while fetching the data from external system", ex);
			}
		} catch (RestClientException rce) {
			throw new CoreApplicationException("Error Occured while fetching the data from external system", rce);
		}
		return requestedData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yeshwr.external.service.ExternalService#getData(int)
	 */
	public RequestedData getData(int id) throws CoreApplicationException {
		RequestedData requestedData = null;
		String formedUrl = getExternalUrl(id);
		try {
			log.info("Calling the external url: "+formedUrl);
			requestedData = restTemplate.getForObject(formedUrl, RequestedData.class);
			log.info("Response from the external system: {}",requestedData);
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new ResourceNotFoundException(ex);
			} else {
				throw new CoreApplicationException("Error Occured while fetching the data from external system", ex);
			}
		} catch (RestClientException rce) {
			throw new CoreApplicationException("Error Occured while fetching the data from external system", rce);
		}
		return requestedData;
	}

	/**
	 * 
	 * Build external url
	 * 
	 * @param id
	 * @return
	 */
	private String getExternalUrl(int id) {
		StringBuilder createUrl = new StringBuilder();
		createUrl.append(externalUrl).append("/").append(id);
		return createUrl.toString();
	}

}
