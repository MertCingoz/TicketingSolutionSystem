package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.TicketElement;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.TicketElementDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.TicketElementService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.TicketElementDeleteServiceResponse;
import com.biletway.service.response.TicketElementGetAllListResponse;
import com.biletway.service.response.TicketElementGetByIdResponse;
import com.biletway.service.response.TicketElementInsertServiceResponse;
import com.biletway.service.response.TicketElementUpdateServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class TicketElementServiceImpl implements TicketElementService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	TicketElementDao ticketElementDao = (TicketElementDao) ApplicationContextUtil.getApplicationContext()
			.getBean("ticketElementDao");

	@Override
	public ServiceResponse insert(BigInteger ticketDesignId, String elementType, String elementId, int xAxis, int yAxis,
			int order, String fieldType, int width, int height, String picture, String font, String size,
			String dbFileName) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketElement ticketElement = new TicketElement();
		ticketElement.setDbFileName(dbFileName);
		ticketElement.setElementId(elementId);
		ticketElement.setElementType(elementType);
		ticketElement.setFieldType(fieldType);
		ticketElement.setFont(font);
		ticketElement.setHeight(height);
		ticketElement.setOrder(order);
		ticketElement.setPicture(picture);
		ticketElement.setSize(size);
		ticketElement.setTicketDesignId(ticketDesignId);
		ticketElement.setWidth(width);
		ticketElement.setxAxis(xAxis);
		ticketElement.setyAxis(yAxis);
		ticketElement.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(ticketElement);
		TicketElementInsertServiceResponse serviceResponse = new TicketElementInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_TICKET_ELEMENT_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_TICKET_ELEMENT_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String elementType, String elementId, int xAxis, int yAxis, int order,
			String fieldType, int width, int height, String picture, String font, String size, String dbFileName) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketElement ticketElement = ticketElementDao.getById(id);
		ticketElement.setId(id);
		ticketElement.setDbFileName(dbFileName);
		ticketElement.setElementId(elementId);
		ticketElement.setElementType(elementType);
		ticketElement.setFont(font);
		ticketElement.setHeight(height);
		ticketElement.setHeight(height);
		ticketElement.setOrder(order);
		ticketElement.setPicture(picture);
		ticketElement.setSize(size);
		ticketElement.setStatus(DaoConstants.ACTIVE_STATUS);
		ticketElement.setWidth(width);
		ticketElement.setxAxis(xAxis);
		ticketElement.setyAxis(yAxis);
		boolean updateResponse = commonOperationsDao.update(ticketElement);
		TicketElementUpdateServiceResponse serviceResponse = new TicketElementUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_TICKET_ELEMENT_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_TICKET_ELEMENT_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse delete(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketElement ticketElement = new TicketElement();
		ticketElement.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(ticketElement);
		TicketElementDeleteServiceResponse serviceResponse = new TicketElementDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_TICKET_ELEMENT_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_TICKET_ELEMENT_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getTicketElementById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketElementGetByIdResponse serviceResponse = new TicketElementGetByIdResponse();
		TicketElement ticketElement = ticketElementDao.getById(id);
		if (ticketElement == null) {
			errorCode = ServiceResponseConstants.GET_TICKET_ELEMENT_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_TICKET_ELEMENT_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setDbFileName(ticketElement.getDbFileName());
			serviceResponse.setElementId(ticketElement.getElementId());
			serviceResponse.setElementType(ticketElement.getElementType());
			serviceResponse.setFieldType(ticketElement.getFieldType());
			serviceResponse.setFont(ticketElement.getFont());
			serviceResponse.setHeight(ticketElement.getHeight());
			serviceResponse.setId(ticketElement.getId());
			serviceResponse.setOrder(ticketElement.getOrder());
			serviceResponse.setPicture(ticketElement.getPicture());
			serviceResponse.setSize(ticketElement.getSize());
			serviceResponse.setStatus(ticketElement.getStatus());
			serviceResponse.setTicketDesignId(ticketElement.getTicketDesignId());
			serviceResponse.setWidth(ticketElement.getWidth());
			serviceResponse.setxAxis(ticketElement.getxAxis());
			serviceResponse.setyAxis(ticketElement.getyAxis());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllTicketElementsByTicketDesignId(BigInteger ticketDesignId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketElementGetAllListResponse serviceResponse = new TicketElementGetAllListResponse();
		List<TicketElement> ticketElementList = ticketElementDao.getAllTicketElementsByTicketDesignId(ticketDesignId);
		if (ticketElementList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_TICKET_ELEMENTS_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_TICKET_ELEMENTS_ERROR_DESC;
		} else {
			serviceResponse.setTicketElementList(ticketElementList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
