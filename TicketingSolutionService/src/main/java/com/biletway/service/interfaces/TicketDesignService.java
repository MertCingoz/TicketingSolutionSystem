package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface TicketDesignService {
	ServiceResponse insert(String backgroundImage, String name);

	ServiceResponse update(BigInteger id, String name, String backgroundImage);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getTicketDesignById(BigInteger id);

	ServiceResponse getAllTicketDesigns();
}
