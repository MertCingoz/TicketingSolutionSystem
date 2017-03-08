package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface PriceListService {
	ServiceResponse insert(String name);

	ServiceResponse update(BigInteger id, String name);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getPriceListById(BigInteger id);

	ServiceResponse getAllPriceLists();
}
