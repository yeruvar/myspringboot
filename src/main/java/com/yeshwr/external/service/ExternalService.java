package com.yeshwr.external.service;

import java.util.List;

import com.yeshwr.exception.CoreApplicationException;
import com.yeshwr.external.model.RequestedData;

/**
 * @author yeshwr
 *
 */
public interface ExternalService {

	/**
	 * Get data from the external api
	 * 
	 * @return Array of requested data
	 * @throws CoreApplicationException
	 */
	public List<RequestedData> getData() throws CoreApplicationException;

	/**
	 * Get data from the external api for the given id
	 * 
	 * @param id
	 * @return RequestedData
	 * @throws CoreApplicationException
	 */
	public RequestedData getData(int id) throws CoreApplicationException;
}
