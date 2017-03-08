package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface TicketElementService {
	ServiceResponse insert(BigInteger ticketDesignId, String elementType, String elementId, int xAxis, int yAxis,
			int order, String fieldType, int width, int height, String picture, String font, String size,
			String dbFileName);

	ServiceResponse update(BigInteger id, String elementType, String elementId, int xAxis, int yAxis, int order,
			String fieldType, int width, int height, String picture, String font, String size, String dbFileName);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getTicketElementById(BigInteger id);

	ServiceResponse getAllTicketElementsByTicketDesignId(BigInteger ticketDesignId);

}
