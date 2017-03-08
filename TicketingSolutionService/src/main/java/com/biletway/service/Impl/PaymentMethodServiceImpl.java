package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.PaymentMethod;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.PaymentMethodDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.PaymentMethodService;
import com.biletway.service.response.PaymentMethodDeleteServiceResponse;
import com.biletway.service.response.PaymentMethodGetAllListResponse;
import com.biletway.service.response.PaymentMethodGetByIdResponse;
import com.biletway.service.response.PaymentMethodInsertServiceResponse;
import com.biletway.service.response.PaymentMethodUpdateServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class PaymentMethodServiceImpl implements PaymentMethodService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	PaymentMethodDao paymentMethodDao = (PaymentMethodDao) ApplicationContextUtil.getApplicationContext()
			.getBean("paymentMethodDao");

	@Override
	public ServiceResponse insert(String name) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setName(name);
		paymentMethod.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(paymentMethod);
		PaymentMethodInsertServiceResponse serviceResponse = new PaymentMethodInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_PAYMENT_METHOD_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_PAYMENT_METHOD_ERROR_DESC;
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
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setId(id);
		paymentMethod.setName(name);
		paymentMethod.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(paymentMethod);
		PaymentMethodUpdateServiceResponse serviceResponse = new PaymentMethodUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_PAYMENT_METHOD_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_PAYMENT_METHOD_ERROR_DESC;
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
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(paymentMethod);
		PaymentMethodDeleteServiceResponse serviceResponse = new PaymentMethodDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_PAYMENT_METHOD_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_PAYMENT_METHOD_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getPaymentMethodById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PaymentMethodGetByIdResponse serviceResponse = new PaymentMethodGetByIdResponse();
		PaymentMethod paymentMethod = paymentMethodDao.getById(id);
		if (paymentMethod == null) {
			errorCode = ServiceResponseConstants.GET_PAYMENT_METHOD_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_PAYMENT_METHOD_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(paymentMethod.getId());
			serviceResponse.setName(paymentMethod.getName());
			serviceResponse.setStatus(paymentMethod.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllPaymentMethods() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PaymentMethodGetAllListResponse serviceResponse = new PaymentMethodGetAllListResponse();
		List<PaymentMethod> paymentMethodList = paymentMethodDao.getAllList();
		if (paymentMethodList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_PAYMENT_METHODS_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_PAYMENT_METHODS_ERROR_DESC;
		} else {
			serviceResponse.setPaymentMethodList(paymentMethodList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
