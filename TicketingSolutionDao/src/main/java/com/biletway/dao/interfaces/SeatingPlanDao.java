package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatingPlan;

public interface SeatingPlanDao {

	SeatingPlan getById(BigInteger id);

	List<SeatingPlan> getAllByHallId(BigInteger id);

	List<SeatingPlan> searchSeatingPlanByName(String name);

}
