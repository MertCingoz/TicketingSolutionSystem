package com.biletway.service.Impl;

import java.math.BigInteger;

import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.service.Impl.multithread.TicketBarcodeGeneration;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.TicketPoolService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.TicketPoolGenerateBarcodeServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceHelper;

public class TicketPoolServiceImpl implements TicketPoolService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	@Override
	public ServiceResponse generateTicketBarcodeForEvent(BigInteger eventId,BigInteger showId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Runnable runnable = new TicketBarcodeGeneration(eventId,showId);
		Thread thread = new Thread(runnable);
		thread.start();
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		TicketPoolGenerateBarcodeServiceResponse serviceResponse = new TicketPoolGenerateBarcodeServiceResponse();
		serviceResponse.setResult(true);
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
