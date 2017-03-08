package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.SeatCategory;

public interface SeatCategoryDao {

	SeatCategory getById(BigInteger id);

	List<SeatCategory> getAllList();

	List<SeatCategory> searchSeatCategoryByName(String name);
}
