package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface SeatDivService {
	ServiceResponse insert(BigInteger seatDesignId, String divId, String seatType, String seatCategory);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getSeatDivById(BigInteger id);

	ServiceResponse getSeatDivBySeatDesignId(BigInteger seatDesignId);

	ServiceResponse deleteAllSeatDivBySeatDesignId(BigInteger seatDesignId);
}
