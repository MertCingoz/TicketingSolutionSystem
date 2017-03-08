package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface PriceTypeService {
	ServiceResponse insert(String name, String reportName);

	ServiceResponse update(BigInteger id, String name, String reportName);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getPriceTypeById(BigInteger id);

	ServiceResponse getAllPriceTypes();
}
