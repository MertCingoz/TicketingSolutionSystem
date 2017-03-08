package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatingPlan;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.SeatingPlanDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.SeatingPlanService;
import com.biletway.service.response.SeatingPlanDeleteServiceResponse;
import com.biletway.service.response.SeatingPlanGetAllListResponse;
import com.biletway.service.response.SeatingPlanGetByIdResponse;
import com.biletway.service.response.SeatingPlanImageUpdateServiceResponse;
import com.biletway.service.response.SeatingPlanInsertServiceResponse;
import com.biletway.service.response.SeatingPlanUpdateServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class SeatingPlanServiceImpl implements SeatingPlanService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;

	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	SeatingPlanDao seatingPlanDao = (SeatingPlanDao) ApplicationContextUtil.getApplicationContext()
			.getBean("seatingPlanDao");

	@Override
	public ServiceResponse insert(String name, BigInteger hallId, String background) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlan seatingPlan = new SeatingPlan();
		seatingPlan.setBackground(background);
		seatingPlan.setHallId(hallId);
		seatingPlan.setName(name);
		seatingPlan.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(seatingPlan);
		SeatingPlanInsertServiceResponse serviceResponse = new SeatingPlanInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_SEATING_PLAN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SEATING_PLAN_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String name, String background) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlan seatingPlan = seatingPlanDao.getById(id);
		seatingPlan.setBackground(background);
		seatingPlan.setName(name);
		seatingPlan.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(seatingPlan);
		SeatingPlanUpdateServiceResponse serviceResponse = new SeatingPlanUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_SEATING_PLAN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_SEATING_PLAN_ERROR_DESC;
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
		SeatingPlan seatingPlan = new SeatingPlan();
		seatingPlan.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(seatingPlan);
		SeatingPlanDeleteServiceResponse serviceResponse = new SeatingPlanDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_SEATING_PLAN_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_SEATING_PLAN_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatingPlanById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlanGetByIdResponse serviceResponse = new SeatingPlanGetByIdResponse();
		SeatingPlan seatingPlan = seatingPlanDao.getById(id);
		if (seatingPlan == null) {
			errorCode = ServiceResponseConstants.GET_SEATING_PLAN_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEATING_PLAN_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(seatingPlan.getId());
			serviceResponse.setName(seatingPlan.getName());
			serviceResponse.setStatus(seatingPlan.getStatus());
			serviceResponse.setHallId(seatingPlan.getHallId());
			serviceResponse.setBackground(seatingPlan.getBackground());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllSeatingPlansByHallId(BigInteger hallId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlanGetAllListResponse serviceResponse = new SeatingPlanGetAllListResponse();
		List<SeatingPlan> seatingPlanList = seatingPlanDao.getAllByHallId(hallId);
		if (seatingPlanList == null) {
			errorCode = ServiceResponseConstants.GET_SEATING_PLAN_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEATING_PLAN_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setSeatingPlanList(seatingPlanList);
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse updateSeatingPlanImage(BigInteger id, String background) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatingPlan seatingPlan = seatingPlanDao.getById(id);
		seatingPlan.setBackground(background);
		seatingPlan.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(seatingPlan);
		SeatingPlanImageUpdateServiceResponse serviceResponse = new SeatingPlanImageUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_SEATING_PLAN_IMAGE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_SEATING_PLAN_IMAGE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
