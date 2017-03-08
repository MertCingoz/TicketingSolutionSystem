package com.biletway.service.Impl;

import java.util.List;

import com.biletway.dao.entity.City;
import com.biletway.dao.interfaces.CityDao;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.CityService;
import com.biletway.service.response.CityGetAllListResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceHelper;

public class CityServiceImpl implements CityService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CityDao cityDao = (CityDao) ApplicationContextUtil.getApplicationContext().getBean("cityDao");

	@Override
	public ServiceResponse getAllCities() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		CityGetAllListResponse serviceResponse = new CityGetAllListResponse();
		List<City> cityList = cityDao.getAllList();
		if (cityList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_CITIES_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_CITIES_ERROR_DESC;
		} else {
			serviceResponse.setCityList(cityList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
