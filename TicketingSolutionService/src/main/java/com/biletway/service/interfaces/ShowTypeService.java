package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface ShowTypeService {
	ServiceResponse insert(String name);

	ServiceResponse update(BigInteger id, String name);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getShowTypeById(BigInteger id);

	ServiceResponse getAllShowTypes();
}
