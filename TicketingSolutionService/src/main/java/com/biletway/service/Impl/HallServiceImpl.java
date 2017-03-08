package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Component;

import com.biletway.dao.entity.Hall;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.HallDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.HallService;
import com.biletway.service.response.HallDeleteServiceResponse;
import com.biletway.service.response.HallGetAllListByVenueIdResponse;
import com.biletway.service.response.HallGetByIdResponse;
import com.biletway.service.response.HallInsertServiceResponse;
import com.biletway.service.response.HallUpdateServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

@Component
public class HallServiceImpl implements HallService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	HallDao hallDao = (HallDao) ApplicationContextUtil.getApplicationContext().getBean("hallDao");
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	@Override
	public ServiceResponse insert(String name, BigInteger venueId, String allowOverlapEvents) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Hall hall = new Hall();
		hall.setAllowOverlapEvents(allowOverlapEvents);
		hall.setName(name);
		hall.setVenueid(venueId);
		hall.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(hall);
		HallInsertServiceResponse serviceResponse = new HallInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_HALL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_HALL_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String name, String allowOverlapEvents) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Hall hall = hallDao.getById(id);
		hall.setAllowOverlapEvents(allowOverlapEvents);
		hall.setName(name);
		hall.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(hall);
		HallUpdateServiceResponse serviceResponse = new HallUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_HALL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_HALL_ERROR_DESC;
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
		Hall hall = new Hall();
		hall.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(hall);
		HallDeleteServiceResponse serviceResponse = new HallDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_HALL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_HALL_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getHallById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		HallGetByIdResponse serviceResponse = new HallGetByIdResponse();
		Hall hall = hallDao.getById(id);
		if (hall == null) {
			errorCode = ServiceResponseConstants.GET_HALL_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_HALL_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setAllowOverlapEvents(hall.getAllowOverlapEvents());
			serviceResponse.setId(hall.getId());
			serviceResponse.setName(hall.getName());
			serviceResponse.setStatus(hall.getStatus());
			serviceResponse.setVenueId(hall.getVenueid());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllHallsByVenueId(BigInteger venueId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		HallGetAllListByVenueIdResponse serviceResponse = new HallGetAllListByVenueIdResponse();
		List<Hall> hallList = hallDao.getAllHallsByVenueId(venueId);
		if (hallList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_HALL_BY_VENUE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_HALL_BY_VENUE_ERROR_DESC;
		} else {
			serviceResponse.setHallList(hallList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
