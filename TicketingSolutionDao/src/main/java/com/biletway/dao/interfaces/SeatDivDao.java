package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatDiv;

public interface SeatDivDao {

	SeatDiv getById(BigInteger id);

	List<SeatDiv> getBySeatDesignId(BigInteger id);

}
