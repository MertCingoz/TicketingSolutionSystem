package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.ShowPaymentMethod;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.ShowPaymentMethodDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.ShowPaymentMethodService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.ShowPaymentMethodDeleteServiceResponse;
import com.biletway.service.response.ShowPaymentMethodGetAllListByShowIdResponse;
import com.biletway.service.response.ShowPaymentMethodInsertServiceResponse;
import com.biletway.service.response.ShowPaymentMethodUpdateServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class ShowPaymentMethodServiceImpl implements ShowPaymentMethodService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;

	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	ShowPaymentMethodDao showPaymentMethodDao = (ShowPaymentMethodDao) ApplicationContextUtil.getApplicationContext()
			.getBean("showPaymentMethodDao");

	@Override
	public ServiceResponse insert(BigInteger showId, BigInteger paymentMethodId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowPaymentMethod showPaymentMethod = new ShowPaymentMethod();
		showPaymentMethod.setPaymentMethodId(paymentMethodId);
		showPaymentMethod.setShowId(showId);
		showPaymentMethod.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(showPaymentMethod);
		ShowPaymentMethodInsertServiceResponse serviceResponse = new ShowPaymentMethodInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_SHOW_PAYMENT_METHOD_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SHOW_PAYMENT_METHOD_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, BigInteger showId, BigInteger paymentMethodId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowPaymentMethod showPaymentMethod = showPaymentMethodDao.getById(id);
		showPaymentMethod.setPaymentMethodId(paymentMethodId);
		showPaymentMethod.setShowId(showId);
		showPaymentMethod.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(showPaymentMethod);
		ShowPaymentMethodUpdateServiceResponse serviceResponse = new ShowPaymentMethodUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_SHOW_PAYMENT_METHOD_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_SHOW_PAYMENT_METHOD_ERROR_DESC;
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
		ShowPaymentMethod showPaymentMethod = new ShowPaymentMethod();
		showPaymentMethod.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(showPaymentMethod);
		ShowPaymentMethodDeleteServiceResponse serviceResponse = new ShowPaymentMethodDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_SHOW_PAYMENT_METHOD_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_SHOW_PAYMENT_METHOD_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getPaymentMethodsByShowId(BigInteger showId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowPaymentMethodGetAllListByShowIdResponse serviceResponse = new ShowPaymentMethodGetAllListByShowIdResponse();
		List<ShowPaymentMethod> showPaymentMethodList = showPaymentMethodDao.getPaymentMethodsByShowId(showId);
		if (showPaymentMethodList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_SHOWS_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_SHOWS_ERROR_DESC;
		} else {
			serviceResponse.setShowPaymentMethodList(showPaymentMethodList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
