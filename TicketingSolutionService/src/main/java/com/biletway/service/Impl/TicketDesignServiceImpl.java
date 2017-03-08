package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.TicketDesign;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.TicketDesignDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.TicketDesignService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.TicketDesignDeleteServiceResponse;
import com.biletway.service.response.TicketDesignGetAllListResponse;
import com.biletway.service.response.TicketDesignGetByIdResponse;
import com.biletway.service.response.TicketDesignInsertServiceResponse;
import com.biletway.service.response.TicketDesignUpdateServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class TicketDesignServiceImpl implements TicketDesignService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	TicketDesignDao ticketDesignDao = (TicketDesignDao) ApplicationContextUtil.getApplicationContext()
			.getBean("ticketDesignDao");

	@Override
	public ServiceResponse insert(String backgroundImage, String name) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketDesign ticketDesign = new TicketDesign();
		ticketDesign.setBackgroundImage(backgroundImage);
		ticketDesign.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		ticketDesign.setName(name);
		TicketDesign insertResponse = (TicketDesign) commonOperationsDao.insertAndGetEntity(ticketDesign);
		TicketDesignInsertServiceResponse serviceResponse = new TicketDesignInsertServiceResponse();
		serviceResponse.setTicketDesign(insertResponse);
		if (insertResponse == null) {
			errorCode = ServiceResponseConstants.CREATE_TICKET_DESIGN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_TICKET_DESIGN_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String name, String backgroundImage) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketDesign ticketDesign = ticketDesignDao.getById(id);
		ticketDesign.setId(id);
		if (!name.equals("")) {
			ticketDesign.setName(name);
		}
		if (!backgroundImage.equals("")) {
			ticketDesign.setBackgroundImage(backgroundImage);
		}
		ticketDesign.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(ticketDesign);
		TicketDesignUpdateServiceResponse serviceResponse = new TicketDesignUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_TICKET_DESIGN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_TICKET_DESIGN_ERROR_DESC;
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
		TicketDesign ticketDesign = new TicketDesign();
		ticketDesign.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(ticketDesign);
		TicketDesignDeleteServiceResponse serviceResponse = new TicketDesignDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_TICKET_DESIGN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_TICKET_DESIGN_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getTicketDesignById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketDesignGetByIdResponse serviceResponse = new TicketDesignGetByIdResponse();
		TicketDesign ticketDesign = ticketDesignDao.getById(id);
		if (ticketDesign == null) {
			errorCode = ServiceResponseConstants.GET_TICKET_DESIGN_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_TICKET_DESIGN_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setBackgroundImage(ticketDesign.getBackgroundImage());
			serviceResponse.setId(ticketDesign.getId());
			serviceResponse.setName(ticketDesign.getName());
			serviceResponse.setStatus(ticketDesign.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllTicketDesigns() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		TicketDesignGetAllListResponse serviceResponse = new TicketDesignGetAllListResponse();
		List<TicketDesign> ticketDesignList = ticketDesignDao.getAllList();
		if (ticketDesignList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_TICKET_DESIGNS_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_TICKET_DESIGNS_ERROR_DESC;
		} else {
			serviceResponse.setTicketDesignList(ticketDesignList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
