package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface HallService {

	ServiceResponse insert(String name, BigInteger venueId, String allowOverlapEvents);

	ServiceResponse update(BigInteger id, String name, String allowOverlapEvents);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getHallById(BigInteger id);

	ServiceResponse getAllHallsByVenueId(BigInteger venueId);

}