package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatCategory;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.SeatCategoryDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.SeatCategoryService;
import com.biletway.service.response.SeatCategoryDeleteServiceResponse;
import com.biletway.service.response.SeatCategoryGetAllListResponse;
import com.biletway.service.response.SeatCategoryGetByIdResponse;
import com.biletway.service.response.SeatCategoryInsertServiceResponse;
import com.biletway.service.response.SeatCategoryUpdateServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class SeatCategoryServiceImpl implements SeatCategoryService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;

	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	SeatCategoryDao seatCategoryDao = (SeatCategoryDao) ApplicationContextUtil.getApplicationContext()
			.getBean("seatCategoryDao");

	@Override
	public ServiceResponse insert(String name, String color) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatCategory seatCategory = new SeatCategory();
		seatCategory.setColor(color);
		seatCategory.setName(name);
		seatCategory.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(seatCategory);
		SeatCategoryInsertServiceResponse serviceResponse = new SeatCategoryInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_SEAT_CATEGORY_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SEAT_CATEGORY_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String name, String color) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatCategory seatCategory = new SeatCategory();
		seatCategory.setId(id);
		seatCategory.setColor(color);
		seatCategory.setName(name);
		seatCategory.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(seatCategory);
		SeatCategoryUpdateServiceResponse serviceResponse = new SeatCategoryUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_SEAT_CATEGORY_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_SEAT_CATEGORY_ERROR_DESC;
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
		SeatCategory seatCategory = new SeatCategory();
		seatCategory.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(seatCategory);
		SeatCategoryDeleteServiceResponse serviceResponse = new SeatCategoryDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_SEAT_CATEGORY_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_SEAT_CATEGORY_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getSeatCategoryById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatCategoryGetByIdResponse serviceResponse = new SeatCategoryGetByIdResponse();
		SeatCategory seatCategory = seatCategoryDao.getById(id);
		if (seatCategory == null) {
			errorCode = ServiceResponseConstants.GET_SEAT_CATEGORY_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SEAT_CATEGORY_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setColor(seatCategory.getColor());
			serviceResponse.setId(seatCategory.getId());
			serviceResponse.setName(seatCategory.getName());
			serviceResponse.setStatus(seatCategory.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllSeatCategories() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		SeatCategoryGetAllListResponse serviceResponse = new SeatCategoryGetAllListResponse();
		List<SeatCategory> seatCategoryList = seatCategoryDao.getAllList();
		if (seatCategoryList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_SEAT_CATEGORIES_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_SEAT_CATEGORIES_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setSeatCategoryList(seatCategoryList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
