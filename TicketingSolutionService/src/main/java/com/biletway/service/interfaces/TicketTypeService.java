package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface TicketTypeService {
	ServiceResponse insert(String name);

	ServiceResponse update(BigInteger id, String name);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getTicketTypeById(BigInteger id);

	ServiceResponse getAllTicketTypes();
}
