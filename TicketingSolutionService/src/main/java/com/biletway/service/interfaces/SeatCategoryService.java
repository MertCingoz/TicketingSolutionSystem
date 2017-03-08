package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface SeatCategoryService {
	ServiceResponse insert(String name, String color);

	ServiceResponse update(BigInteger id, String name, String color);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getSeatCategoryById(BigInteger id);

	ServiceResponse getAllSeatCategories();
}
