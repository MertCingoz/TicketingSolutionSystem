package com.biletway.service.Impl;

import com.biletway.dao.interfaces.ApplicationSettingsDao;
import com.biletway.service.interfaces.ApplicationSettingsService;
import com.biletway.service.util.ApplicationContextUtil;

public class ApplicationSettingsServiceImpl implements ApplicationSettingsService {
	ApplicationSettingsDao applicationSettingsDao = (ApplicationSettingsDao) ApplicationContextUtil
			.getApplicationContext().getBean("applicationSettingsDao");

	/*
	 * @Override public ServiceResponse getValueByKey(String key) { errorCode =
	 * ServiceResponseConstants.ERROR_CODE_SUCCESS; errorDesc =
	 * ServiceResponseConstants.ERROR_DESC_SUCCESS; long startTime =
	 * System.currentTimeMillis(); ApplicationSettingsGetValueByKeyResponse
	 * serviceResponse = new ApplicationSettingsGetValueByKeyResponse(); String
	 * value = applicationSettingsDao.getValueByKey(key); if (value == null) {
	 * errorCode = ServiceResponseConstants.
	 * GET_APPLICATION_SETTINGS_VALUE_BY_KEY_ERROR_CODE; errorDesc =
	 * ServiceResponseConstants.
	 * GET_APPLICATION_SETTINGS_VALUE_BY_KEY_ERROR_DESC; } else {
	 * serviceResponse.setValue(value); } long endTime =
	 * System.currentTimeMillis(); long serviceDuration = endTime - startTime;
	 * return ServiceHelper.getServiceResponseObject(errorCode, errorDesc,
	 * serviceDuration, serviceResponse); }
	 */
	@Override
	public String getValueByKey(String key) {
		return applicationSettingsDao.getValueByKey(key);
	}
}
