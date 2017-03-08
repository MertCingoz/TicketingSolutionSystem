package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface ShowPaymentMethodService {
	ServiceResponse insert(BigInteger showId, BigInteger paymentMethodId);

	ServiceResponse update(BigInteger id, BigInteger showId, BigInteger paymentMethodId);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getPaymentMethodsByShowId(BigInteger showId);

}
