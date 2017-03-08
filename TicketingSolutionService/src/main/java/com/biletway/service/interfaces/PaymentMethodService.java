package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface PaymentMethodService {
	ServiceResponse insert(String name);

	ServiceResponse update(BigInteger id, String name);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getPaymentMethodById(BigInteger id);

	ServiceResponse getAllPaymentMethods();
}
