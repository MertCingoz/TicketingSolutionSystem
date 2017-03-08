package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatDesign;
import com.biletway.dao.entity.SeatDiv;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.SeatDesignDao;
import com.biletway.dao.interfaces.SeatDivDao;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.SeatDesignService;
import com.biletway.service.response.SeatDesignDeleteServiceResponse;
import com.biletway.service.response.SeatDesignGetAllByIdResponse;
import com.biletway.service.response.SeatDesignGetByIdResponse;
import com.biletway.service.response.SeatDesignInsertServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class SeatDesignServiceImpl implements SeatDesignService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;

	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	SeatDesignDao seatDesignDao = (SeatDesignDao) ApplicationContextUtil.getApplicationContext()
			.getBean("seatDesignDao");
	SeatDivDao seatDivDao = (SeatDivDao) ApplicationContextUtil.getApplicationContext().getBean("seatDivDao");

	@Override
	public ServiceResponse insert(BigInteger seatingPlanDetailId, String rowName, int seatNumber, String rowPattern,
			int rowOrder, String seatDivs) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDesign seatDesign = new SeatDesign();
		seatDesign.setSeatingPlanDetailId(seatingPlanDetailId);
		seatDesign.setRowName(rowName);
		seatDesign.setSeatNumber(seatNumber);
		seatDesign.setRowPattern(rowPattern);
		seatDesign.setRowOrder(rowOrder);
		seatDesign.setSeatDivs(seatDivs);
		seatDesign.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		SeatDesign insertResponse = (SeatDesign) commonOperationsDao.insertAndGetEntity(seatDesign);
		SeatDesignInsertServiceResponse serviceResponse = new SeatDesignInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (insertResponse == null) {
			errorCode = ServiceResponseConstants.CREATE_SEAT_DESIGN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SEAT_DESIGN_ERROR_DESC;
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
		SeatDesign seatDesign = new SeatDesign();
		seatDesign.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(seatDesign);
		SeatDesignDeleteServiceResponse serviceResponse = new SeatDesignDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_SEAT_DESIGN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_SEAT_DESIGN_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatDesignById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDesignGetByIdResponse serviceResponse = new SeatDesignGetByIdResponse();
		SeatDesign seatDesign = seatDesignDao.getById(id);
		if (seatDesign == null) {
			errorCode = ServiceResponseConstants.GET_SEAT_DESIGN_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEAT_DESIGN_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(seatDesign.getId());
			serviceResponse.setStatus(seatDesign.getStatus());
			serviceResponse.setSeatingPlanDetailId(seatDesign.getSeatingPlanDetailId());
			serviceResponse.setRowName(seatDesign.getRowName());
			serviceResponse.setSeatNumber(seatDesign.getSeatNumber());
			serviceResponse.setRowPattern(seatDesign.getRowPattern());
			serviceResponse.setRowOrder(seatDesign.getRowOrder());
			serviceResponse.setSeatDivs(seatDesign.getSeatDivs());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatDesignBySeatingPlanDetailId(BigInteger seatingPlanDetailId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDesignGetAllByIdResponse serviceResponse = new SeatDesignGetAllByIdResponse();
		List<SeatDesign> seatDesignList = seatDesignDao.getBySeatingPlanDetailId(seatingPlanDetailId);
		if (seatDesignList == null) {
			errorCode = ServiceResponseConstants.GET_SEAT_DESIGN_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEAT_DESIGN_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setSeatDesign(seatDesignList);
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse deleteAllSeatDesignBySeatingPlanDetailId(BigInteger seatingPlanDetailId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatDesignGetAllByIdResponse serviceResponse = new SeatDesignGetAllByIdResponse();
		List<SeatDesign> seatDesignList = seatDesignDao.getBySeatingPlanDetailId(seatingPlanDetailId);
		for (SeatDesign seatDesign : seatDesignList) {
			List<SeatDiv> seatDivList = seatDivDao.getBySeatDesignId(seatDesign.getId());
			for (SeatDiv seatDiv : seatDivList) {
				commonOperationsDao.delete(seatDiv);
			}
			delete(seatDesign.getId());
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
