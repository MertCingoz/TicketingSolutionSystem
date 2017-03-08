package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatDesign;

public interface SeatDesignDao {

	SeatDesign getById(BigInteger id);

	List<SeatDesign> getBySeatingPlanDetailId(BigInteger id);

}
