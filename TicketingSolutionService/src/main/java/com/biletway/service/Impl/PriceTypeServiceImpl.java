package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.PriceType;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.PriceTypeDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.PriceTypeService;
import com.biletway.service.response.PriceTypeDeleteServiceResponse;
import com.biletway.service.response.PriceTypeGetAllListResponse;
import com.biletway.service.response.PriceTypeGetByIdResponse;
import com.biletway.service.response.PriceTypeInsertServiceResponse;
import com.biletway.service.response.PriceTypeUpdateServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class PriceTypeServiceImpl implements PriceTypeService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	PriceTypeDao priceTypeDao = (PriceTypeDao) ApplicationContextUtil.getApplicationContext().getBean("priceTypeDao");

	@Override
	public ServiceResponse insert(String name, String reportName) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceType priceType = new PriceType();
		priceType.setName(name);
		priceType.setReportName(reportName);
		priceType.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(priceType);
		PriceTypeInsertServiceResponse serviceResponse = new PriceTypeInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_PRICE_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_PRICE_TYPE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String name, String reportName) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceType priceType = new PriceType();
		priceType.setId(id);
		priceType.setName(name);
		priceType.setReportName(reportName);
		priceType.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(priceType);
		PriceTypeUpdateServiceResponse serviceResponse = new PriceTypeUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_PRICE_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_PRICE_TYPE_ERROR_DESC;
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
		PriceType priceType = new PriceType();
		priceType.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(priceType);
		PriceTypeDeleteServiceResponse serviceResponse = new PriceTypeDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_PRICE_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_PRICE_TYPE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getPriceTypeById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceTypeGetByIdResponse serviceResponse = new PriceTypeGetByIdResponse();
		PriceType priceType = priceTypeDao.getById(id);
		if (priceType == null) {
			errorCode = ServiceResponseConstants.GET_PRICE_TYPE_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_PRICE_TYPE_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setReportName(priceType.getReportName());
			serviceResponse.setId(priceType.getId());
			serviceResponse.setName(priceType.getName());
			serviceResponse.setStatus(priceType.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllPriceTypes() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceTypeGetAllListResponse serviceResponse = new PriceTypeGetAllListResponse();
		List<PriceType> priceTypeList = priceTypeDao.getAllList();
		if (priceTypeList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_PRICE_LISTS_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_PRICE_LISTS_ERROR_DESC;
		} else {
			serviceResponse.setPriceTypeList(priceTypeList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
