package com.biletway.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.biletway.rest.resonse.RestResponse;
import com.biletway.service.response.ServiceResponse;

public class CommonHelper {

	public static String getUUID() {
		return java.util.UUID.randomUUID().toString();
	}

	public static RestResponse getResponseObjectFromServiceResponse(ServiceResponse serviceResponse) {
		RestResponse restResponseObject = new RestResponse();
		restResponseObject.setErrorCode(serviceResponse.getErrorCode());
		restResponseObject.setErrorDesc(serviceResponse.getErrorDesc());
		restResponseObject.setResponse(serviceResponse.getResponse());
		restResponseObject.setServiceDuration(serviceResponse.getServiceDuration());
		return restResponseObject;
	}

	public static Date dateConvertor(String date) {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		Date parse = null;
		try {
			parse = sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parse;
	}
}
