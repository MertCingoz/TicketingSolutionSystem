package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.ShowType;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.ShowTypeDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.ShowTypeService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.ShowTypeDeleteServiceResponse;
import com.biletway.service.response.ShowTypeGetAllListResponse;
import com.biletway.service.response.ShowTypeGetByIdResponse;
import com.biletway.service.response.ShowTypeInsertServiceResponse;
import com.biletway.service.response.ShowTypeUpdateServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class ShowTypeServiceImpl implements ShowTypeService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	ShowTypeDao showTypeDao = (ShowTypeDao) ApplicationContextUtil.getApplicationContext().getBean("showTypeDao");

	@Override
	public ServiceResponse insert(String name) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowType showType = new ShowType();
		showType.setName(name);
		showType.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(showType);
		ShowTypeInsertServiceResponse serviceResponse = new ShowTypeInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_SHOW_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SHOW_TYPE_ERROR_DESC;
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
		ShowType showType = new ShowType();
		showType.setId(id);
		showType.setName(name);
		showType.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(showType);
		ShowTypeUpdateServiceResponse serviceResponse = new ShowTypeUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_SHOW_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_SHOW_TYPE_ERROR_DESC;
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
		ShowType showType = new ShowType();
		showType.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(showType);
		ShowTypeDeleteServiceResponse serviceResponse = new ShowTypeDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_SHOW_TYPE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_SHOW_TYPE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getShowTypeById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowTypeGetByIdResponse serviceResponse = new ShowTypeGetByIdResponse();
		ShowType showType = showTypeDao.getById(id);
		if (showType == null) {
			errorCode = ServiceResponseConstants.GET_SHOW_TYPE_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SHOW_TYPE_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(showType.getId());
			serviceResponse.setName(showType.getName());
			serviceResponse.setStatus(showType.getStatus());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllShowTypes() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowTypeGetAllListResponse serviceResponse = new ShowTypeGetAllListResponse();
		List<ShowType> showTypeList = showTypeDao.getAllList();
		if (showTypeList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_SHOW_TYPES_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_SHOW_TYPES_ERROR_DESC;
		} else {
			serviceResponse.setShowTypeList(showTypeList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
