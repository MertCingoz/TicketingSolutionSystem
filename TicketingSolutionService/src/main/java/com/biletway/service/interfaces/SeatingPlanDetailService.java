package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface SeatingPlanDetailService {
	ServiceResponse insert(BigInteger seatingPlanDetailId, BigInteger seatingPlanId, BigInteger hallBlockId,
			int hallBlockCapacity, String hasSeatingPlan, String drawingArea);

	ServiceResponse delete(BigInteger id);

	ServiceResponse changeSeatingPlanDetailStatusToPassive(BigInteger seatingPlanId, String activeSeatingPlanDetailIds);

	ServiceResponse getSeatingPlanDetailById(BigInteger id);

	ServiceResponse getSeatingPlanDetailBySeatingPlanId(BigInteger seatingPlanId);

	//ServiceResponse deleteAllSeatingPlanDetailBySeatingPlanId(BigInteger seatingPlanId);
}
