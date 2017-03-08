package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatingPlanDetail;

public interface SeatingPlanDetailDao {

	SeatingPlanDetail getById(BigInteger id);

	List<SeatingPlanDetail> getBySeatingPlanId(BigInteger id);

}
