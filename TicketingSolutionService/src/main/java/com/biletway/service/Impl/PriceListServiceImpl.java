package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.PriceList;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.PriceListDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.PriceListService;
import com.biletway.service.response.PriceListDeleteServiceResponse;
import com.biletway.service.response.PriceListGetAllListResponse;
import com.biletway.service.response.PriceListGetByIdResponse;
import com.biletway.service.response.PriceListInsertServiceResponse;
import com.biletway.service.response.PriceListUpdateServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class PriceListServiceImpl implements PriceListService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	PriceListDao priceListDao = (PriceListDao) ApplicationContextUtil.getApplicationContext().getBean("priceListDao");

	@Override
	public ServiceResponse insert(String name) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceList priceList = new PriceList();
		priceList.setName(name);
		priceList.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(priceList);
		PriceListInsertServiceResponse serviceResponse = new PriceListInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_PRICE_LIST_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_PRICE_LIST_ERROR_DESC;
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
		PriceList priceList = new PriceList();
		priceList.setId(id);
		priceList.setName(name);
		priceList.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(priceList);
		PriceListUpdateServiceResponse serviceResponse = new PriceListUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_PRICE_LIST_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_PRICE_LIST_ERROR_DESC;
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
		PriceList priceList = new PriceList();
		priceList.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(priceList);
		PriceListDeleteServiceResponse serviceResponse = new PriceListDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_PRICE_LIST_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_PRICE_LIST_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getPriceListById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceListGetByIdResponse serviceResponse = new PriceListGetByIdResponse();
		PriceList priceList = priceListDao.getById(id);
		if (priceList == null) {
			errorCode = ServiceResponseConstants.GET_PRICE_LIST_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_PRICE_LIST_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(priceList.getId());
			serviceResponse.setName(priceList.getName());
			serviceResponse.setStatus(priceList.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllPriceLists() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceListGetAllListResponse serviceResponse = new PriceListGetAllListResponse();
		List<PriceList> priceListList = priceListDao.getAllList();
		if (priceListList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_PRICE_LISTS_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_PRICE_LISTS_ERROR_DESC;
		} else {
			serviceResponse.setPriceTypeList(priceListList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
