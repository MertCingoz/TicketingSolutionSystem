package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.biletway.dao.entity.SeatingPlanDetail;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.SeatingPlanDetailDao;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.SeatingPlanDetailService;
import com.biletway.service.response.SeatingPlanDetailDeleteServiceResponse;
import com.biletway.service.response.SeatingPlanDetailGetAllByIdResponse;
import com.biletway.service.response.SeatingPlanDetailGetByIdResponse;
import com.biletway.service.response.SeatingPlanDetailInsertServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class SeatingPlanDetailServiceImpl implements SeatingPlanDetailService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;

	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	SeatingPlanDetailDao seatingPlanDetailDao = (SeatingPlanDetailDao) ApplicationContextUtil.getApplicationContext()
			.getBean("seatingPlanDetailDao");

	@Override
	public ServiceResponse insert(BigInteger seatingPlanDetailId, BigInteger seatingPlanId, BigInteger hallBlockId,
			int hallBlockCapacity, String hasSeatingPlan, String drawingArea) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlanDetail seatingPlanDetail = new SeatingPlanDetail();
		seatingPlanDetail.setDrawingArea(drawingArea);
		seatingPlanDetail.setHallBlockCapacity(hallBlockCapacity);
		seatingPlanDetail.setHallBlockId(hallBlockId);
		seatingPlanDetail.setHasSeatingPlan(hasSeatingPlan);
		seatingPlanDetail.setSeatingPlanId(seatingPlanId);
		seatingPlanDetail.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		SeatingPlanDetailInsertServiceResponse serviceResponse = new SeatingPlanDetailInsertServiceResponse();
		SeatingPlanDetail insertResponse;
		if (seatingPlanDetailId.compareTo(new BigInteger("0")) == 0) {
			insertResponse = (SeatingPlanDetail) commonOperationsDao.insertAndGetEntity(seatingPlanDetail);
			serviceResponse.setResult(insertResponse);
		} else {
			seatingPlanDetail.setId(seatingPlanDetailId);
			insertResponse = (SeatingPlanDetail) commonOperationsDao.updateAndGetEntity(seatingPlanDetail);
		}

		serviceResponse.setResult(insertResponse);
		if (insertResponse == null) {
			errorCode = ServiceResponseConstants.CREATE_SEATING_PLAN_DETAIL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SEATING_PLAN_DETAIL_ERROR_DESC;
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
		SeatingPlanDetail seatingPlanDetail = new SeatingPlanDetail();
		seatingPlanDetail.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(seatingPlanDetail);
		SeatingPlanDetailDeleteServiceResponse serviceResponse = new SeatingPlanDetailDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_SEATING_PLAN_DETAIL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_SEATING_PLAN_DETAIL_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatingPlanDetailById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlanDetailGetByIdResponse serviceResponse = new SeatingPlanDetailGetByIdResponse();
		SeatingPlanDetail seatingPlanDetail = seatingPlanDetailDao.getById(id);
		if (seatingPlanDetail == null) {
			errorCode = ServiceResponseConstants.GET_SEATING_PLAN_DETAIL_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEATING_PLAN_DETAIL_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setDrawingArea(seatingPlanDetail.getDrawingArea());
			serviceResponse.setHallBlockCapacity(seatingPlanDetail.getHallBlockCapacity());
			serviceResponse.setHallBlockId(seatingPlanDetail.getHallBlockId());
			serviceResponse.setHasSeatingPlan(seatingPlanDetail.getHasSeatingPlan());
			serviceResponse.setId(seatingPlanDetail.getId());
			serviceResponse.setSeatingPlanId(seatingPlanDetail.getSeatingPlanId());
			serviceResponse.setStatus(seatingPlanDetail.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatingPlanDetailBySeatingPlanId(BigInteger seatingPlanId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlanDetailGetAllByIdResponse serviceResponse = new SeatingPlanDetailGetAllByIdResponse();
		List<SeatingPlanDetail> seatingPlanDetailList = seatingPlanDetailDao.getBySeatingPlanId(seatingPlanId);
		if (seatingPlanDetailList == null) {
			errorCode = ServiceResponseConstants.GET_SEATING_PLAN_DETAIL_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEATING_PLAN_DETAIL_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setSeatingPlanDetailList(seatingPlanDetailList);
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse changeSeatingPlanDetailStatusToPassive(BigInteger seatingPlanId,
			String activeSeatingPlanDetailIds) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		List<BigInteger> bigIntIdList = new ArrayList<BigInteger>();
		String[] stringIdList = activeSeatingPlanDetailIds.split("\\|");
		for (int i = 0; i < stringIdList.length; i++) {
			bigIntIdList.add(new BigInteger(stringIdList[i]));
		}
		SeatingPlanDetailGetAllByIdResponse serviceResponse = new SeatingPlanDetailGetAllByIdResponse();
		List<SeatingPlanDetail> seatingPlanDetailList = seatingPlanDetailDao.getBySeatingPlanId(seatingPlanId);
		for (SeatingPlanDetail seatingPlanDetail : seatingPlanDetailList) {
			if (!bigIntIdList.contains(seatingPlanDetail.getId())) {
				seatingPlanDetail.setStatus("P");
				// delete(seatingPlanDetail.getId());
				commonOperationsDao.update(seatingPlanDetail);
				SeatDesignServiceImpl seatDesignService = new SeatDesignServiceImpl();
				seatDesignService.deleteAllSeatDesignBySeatingPlanDetailId(seatingPlanDetail.getId());

			}
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
