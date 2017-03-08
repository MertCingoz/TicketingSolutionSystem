package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface PriceListDetailService {
	ServiceResponse insert(BigInteger priceTypeId, BigInteger priceListId, BigInteger seatCategoryId, double price);

	ServiceResponse getPriceListDetailList(BigInteger priceListId);

	ServiceResponse getPriceListDetailByShowId(BigInteger showId);

}
