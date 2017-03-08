package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface SeatDesignService {
	ServiceResponse insert(BigInteger seatingPlanDetailId, String rowName, int seatNumber, String rowPattern,
			int rowOrder, String seatDivs);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getSeatDesignById(BigInteger id);

	ServiceResponse getSeatDesignBySeatingPlanDetailId(BigInteger seatingPlanDetailId);

	ServiceResponse deleteAllSeatDesignBySeatingPlanDetailId(BigInteger seatingPlanDetailId);
}
