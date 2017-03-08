package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.PriceList;

public interface PriceListDao {
	PriceList getById(BigInteger id);

	List<PriceList> getAllList();

	List<PriceList> searchPriceListByName(String name);
}
