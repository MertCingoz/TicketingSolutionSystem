package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.PriceType;

public interface PriceTypeDao {
	PriceType getById(BigInteger id);

	List<PriceType> getAllList();

	List<PriceType> searchPriceTypeByName(String name);
}
