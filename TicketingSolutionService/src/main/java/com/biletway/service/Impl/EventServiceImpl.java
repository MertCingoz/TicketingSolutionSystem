package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.biletway.dao.entity.Event;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.EventDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.EventService;
import com.biletway.service.response.EventDeleteServiceResponse;
import com.biletway.service.response.EventGetAllListByShowIdResponse;
import com.biletway.service.response.EventGetByIdResponse;
import com.biletway.service.response.EventInsertServiceResponse;
import com.biletway.service.response.EventUpdateServiceResponse;
import com.biletway.service.response.GetHomePageEventsServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class EventServiceImpl implements EventService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	EventDao eventDao = (EventDao) ApplicationContextUtil.getApplicationContext().getBean("eventDao");

	@Override
	public ServiceResponse insert(BigInteger showId, Date eventDate, int duration, int selectSeat, int saleStatus,
			BigInteger ticketDesignId, Date createDate, BigInteger createUser) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Event event = new Event();
		event.setCreateDate(createDate);
		event.setCreateUser(createUser);
		event.setDuration(duration);
		event.setEventDate(eventDate);
		event.setSaleStatus(saleStatus);
		event.setSelectSeat(selectSeat);
		event.setTicketDesignId(ticketDesignId);
		event.setShowId(showId);
		event.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		event.setBarcodeCreationStatus("STARTED");
		Event insertResponse = (Event) commonOperationsDao.insertAndGetEntity(event);
		EventInsertServiceResponse serviceResponse = new EventInsertServiceResponse();
		if (insertResponse == null) {
			serviceResponse.setResult(true);
			errorCode = ServiceResponseConstants.CREATE_EVENT_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_EVENT_ERROR_DESC;
		} else {
			serviceResponse.setResult(false);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		TicketPoolServiceImpl tps = new TicketPoolServiceImpl();
		tps.generateTicketBarcodeForEvent(insertResponse.getId(), showId);
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, Date eventDate, int duration, int selectSeat, int saleStatus,
			BigInteger ticketDesignId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Event event = eventDao.getById(id);
		event.setDuration(duration);
		event.setEventDate(eventDate);
		event.setSaleStatus(saleStatus);
		event.setSelectSeat(selectSeat);
		event.setTicketDesignId(ticketDesignId);
		event.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(event);
		EventUpdateServiceResponse serviceResponse = new EventUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_EVENT_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_EVENT_ERROR_DESC;
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
		Event event = new Event();
		event.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(event);
		EventDeleteServiceResponse serviceResponse = new EventDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_EVENT_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_EVENT_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getEventById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		EventGetByIdResponse serviceResponse = new EventGetByIdResponse();
		Event event = eventDao.getById(id);
		if (event == null) {
			errorCode = ServiceResponseConstants.GET_EVENT_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_EVENT_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setCreateDate(event.getCreateDate().toString());
			serviceResponse.setCreateUser(event.getCreateUser());
			serviceResponse.setDuration(event.getDuration());
			serviceResponse.setEventDate(event.getEventDate().toString());
			serviceResponse.setId(event.getId());
			serviceResponse.setSaleStatus(event.getSaleStatus());
			serviceResponse.setSelectSeat(event.getSelectSeat());
			serviceResponse.setShowId(event.getShowId());
			serviceResponse.setStatus(event.getStatus());
			serviceResponse.setTicketDesignId(event.getTicketDesignId());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllEventsByShowId(BigInteger showId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		EventGetAllListByShowIdResponse serviceResponse = new EventGetAllListByShowIdResponse();
		List<Event> eventList = eventDao.getAllEventsByShowId(showId);
		if (eventList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_EVENT_BY_SHOW_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_EVENT_BY_SHOW_ERROR_DESC;
		} else {
			serviceResponse.setEventList(eventList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getHomePageEvents() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		GetHomePageEventsServiceResponse serviceResponse = new GetHomePageEventsServiceResponse();
		
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
