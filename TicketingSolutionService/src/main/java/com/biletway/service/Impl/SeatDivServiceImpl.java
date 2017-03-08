package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatDiv;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.SeatDivDao;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.SeatDivService;
import com.biletway.service.response.SeatDivDeleteServiceResponse;
import com.biletway.service.response.SeatDivGetAllByIdResponse;
import com.biletway.service.response.SeatDivGetByIdResponse;
import com.biletway.service.response.SeatDivInsertServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class SeatDivServiceImpl implements SeatDivService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;

	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	SeatDivDao seatDivDao = (SeatDivDao) ApplicationContextUtil.getApplicationContext().getBean("seatDivDao");

	@Override
	public ServiceResponse insert(BigInteger seatDesignId, String divId, String seatType, String seatCategory) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDiv seatDiv = new SeatDiv();
		seatDiv.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		seatDiv.setSeatDesignId(seatDesignId);
		seatDiv.setDivId(divId);
		seatDiv.setSeatType(seatType);
		seatDiv.setSeatCategory(seatCategory);
		boolean insertResponse = commonOperationsDao.insert(seatDiv);
		SeatDivInsertServiceResponse serviceResponse = new SeatDivInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_SEAT_DIV_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SEAT_DIV_ERROR_DESC;
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
		SeatDiv seatDiv = new SeatDiv();
		seatDiv.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(seatDiv);
		SeatDivDeleteServiceResponse serviceResponse = new SeatDivDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_SEAT_DIV_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_SEAT_DIV_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatDivById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDivGetByIdResponse serviceResponse = new SeatDivGetByIdResponse();
		SeatDiv seatDiv = seatDivDao.getById(id);
		if (seatDiv == null) {
			errorCode = ServiceResponseConstants.GET_SEAT_DIV_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEAT_DIV_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(seatDiv.getId());
			serviceResponse.setStatus(seatDiv.getStatus());
			serviceResponse.setSeatDesignId(seatDiv.getSeatDesignId());
			serviceResponse.setDivId(seatDiv.getDivId());
			serviceResponse.setSeatType(seatDiv.getSeatType());
			serviceResponse.setSeatCategory(seatDiv.getSeatCategory());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatDivBySeatDesignId(BigInteger seatDesignId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDivGetAllByIdResponse serviceResponse = new SeatDivGetAllByIdResponse();
		List<SeatDiv> seatDivList = seatDivDao.getBySeatDesignId(seatDesignId);
		if (seatDivList == null) {
			errorCode = ServiceResponseConstants.GET_SEAT_DIV_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEAT_DIV_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setSeatDiv(seatDivList);
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse deleteAllSeatDivBySeatDesignId(BigInteger seatDesignId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDivGetAllByIdResponse serviceResponse = new SeatDivGetAllByIdResponse();
		List<SeatDiv> seatDivList = seatDivDao.getBySeatDesignId(seatDesignId);
		for (SeatDiv seatDiv : seatDivList) {
			delete(seatDiv.getId());
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
