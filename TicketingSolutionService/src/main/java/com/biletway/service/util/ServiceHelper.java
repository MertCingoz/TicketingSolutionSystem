package com.biletway.service.util;

import com.biletway.service.response.ServiceResponse;

public class ServiceHelper {
	public static ServiceResponse getServiceResponseObject(String errorCode, String errorDesc, long serviceDuration,
			Object responseObject) {
		ServiceResponse serviceResponseObject = new ServiceResponse();
		serviceResponseObject.setErrorCode(errorCode);
		serviceResponseObject.setErrorDesc(errorDesc);
		serviceResponseObject.setResponse(responseObject);
		serviceResponseObject.setServiceDuration(serviceDuration);
		return serviceResponseObject;
	}
}
