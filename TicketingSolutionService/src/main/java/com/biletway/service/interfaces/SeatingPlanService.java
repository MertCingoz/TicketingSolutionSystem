package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface SeatingPlanService {
	ServiceResponse insert(String name, BigInteger hallId, String background);

	ServiceResponse update(BigInteger id, String name, String background);

	ServiceResponse updateSeatingPlanImage(BigInteger id, String background);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getSeatingPlanById(BigInteger id);

	ServiceResponse getAllSeatingPlansByHallId(BigInteger hallId);
}
