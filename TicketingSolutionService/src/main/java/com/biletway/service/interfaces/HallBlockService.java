package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface HallBlockService {

	ServiceResponse insert(String name, BigInteger venueId, BigInteger hallId);

	ServiceResponse update(BigInteger id, String name);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getHallBlockById(BigInteger id);

	ServiceResponse getAllHallBlocksByHallId(BigInteger hallId);
}
