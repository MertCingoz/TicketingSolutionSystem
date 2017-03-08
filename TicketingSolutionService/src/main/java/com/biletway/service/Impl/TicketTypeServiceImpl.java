package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.TicketType;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.TicketTypeDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.TicketTypeService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.TicketTypeDeleteServiceResponse;
import com.biletway.service.response.TicketTypeGetAllListResponse;
import com.biletway.service.response.TicketTypeGetByIdResponse;
import com.biletway.service.response.TicketTypeInsertServiceResponse;
import com.biletway.service.response.TicketTypeUpdateServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class TicketTypeServiceImpl implements TicketTypeService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	TicketTypeDao ticketTypeDao = (TicketTypeDao) ApplicationContextUtil.getApplicationContext()
			.getBean("ticketTypeDao");

	@Override
	public ServiceResponse insert(String name) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketType ticketType = new TicketType();
		ticketType.setName(name);
		ticketType.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(ticketType);
		TicketTypeInsertServiceResponse serviceResponse = new TicketTypeInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_TICKET_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_TICKET_TYPE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String name) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketType ticketType = new TicketType();
		ticketType.setId(id);
		ticketType.setName(name);
		ticketType.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(ticketType);
		TicketTypeUpdateServiceResponse serviceResponse = new TicketTypeUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_TICKET_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_TICKET_TYPE_ERROR_DESC;
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
		TicketType ticketType = new TicketType();
		ticketType.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(ticketType);
		TicketTypeDeleteServiceResponse serviceResponse = new TicketTypeDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_TICKET_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_TICKET_TYPE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getTicketTypeById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketTypeGetByIdResponse serviceResponse = new TicketTypeGetByIdResponse();
		TicketType ticketType = ticketTypeDao.getById(id);
		if (ticketType == null) {
			errorCode = ServiceResponseConstants.GET_TICKET_TYPE_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_TICKET_TYPE_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(ticketType.getId());
			serviceResponse.setName(ticketType.getName());
			serviceResponse.setStatus(ticketType.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllTicketTypes() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketTypeGetAllListResponse serviceResponse = new TicketTypeGetAllListResponse();
		List<TicketType> ticketTypeList = ticketTypeDao.getAllList();
		if (ticketTypeList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_TICKET_TYPES_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_TICKET_TYPES_ERROR_DESC;
		} else {
			serviceResponse.setTicketTypeList(ticketTypeList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
