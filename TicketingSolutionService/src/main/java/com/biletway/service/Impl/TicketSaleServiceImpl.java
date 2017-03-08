package com.biletway.service.Impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.TicketSaleService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.TicketSaleServiceSaleTicketByBackOfficeResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceHelper;

public class TicketSaleServiceImpl implements TicketSaleService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");

	@Override
	public ServiceResponse saleTicketByBackOffice(BigInteger showId, BigInteger eventId, String priceTypeIdList,
			String ticketCountList) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		FileInputStream fileInputStream;
		List<byte[]> ticketPdfList = new ArrayList<>();
		try {
			fileInputStream = new FileInputStream("C:\\sana\\1.pdf");
			byte[] pdfContent = new byte[fileInputStream.available()];
			ticketPdfList.add(pdfContent);
			fileInputStream.read(pdfContent, 0, fileInputStream.available());
			ByteBuffer buffer = ByteBuffer.wrap(pdfContent);
			fileInputStream.close();
			fileInputStream = new FileInputStream("C:\\sana\\2.pdf");
			pdfContent = new byte[fileInputStream.available()];
			fileInputStream.read(pdfContent, 0, fileInputStream.available());
			buffer = ByteBuffer.wrap(pdfContent);
			ticketPdfList.add(pdfContent);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		TicketSaleServiceSaleTicketByBackOfficeResponse serviceResponse = new TicketSaleServiceSaleTicketByBackOfficeResponse();
		serviceResponse.setSuccess(true);

		serviceResponse.setTicketPdfList(ticketPdfList);
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
