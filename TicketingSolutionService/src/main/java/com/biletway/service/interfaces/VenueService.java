package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface VenueService {
	ServiceResponse insert(String name, BigInteger countryId, BigInteger stateId, BigInteger cityId, String address,
			String postalCode, String phone, String fax, String email, String venueUrl, String latitude,
			String longitude);

	ServiceResponse update(BigInteger id, String name, BigInteger countryId, BigInteger stateId, BigInteger cityId,
			String address, String postalCode, String phone, String fax, String email, String venueUrl, String latitude,
			String longitude);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getVenueById(BigInteger id);

	ServiceResponse getAllVenues();
}
