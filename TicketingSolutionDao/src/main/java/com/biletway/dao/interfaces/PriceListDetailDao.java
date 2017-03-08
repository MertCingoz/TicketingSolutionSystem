package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.PriceListDetail;

public interface PriceListDetailDao {
	PriceListDetail getPriceListDetail(BigInteger priceListId, BigInteger seatCategoryId, BigInteger priceTypeId);

	List<PriceListDetail> getPriceListDetailList(BigInteger priceListId);

	List<PriceListDetail> getPricedPriceListDetail(BigInteger priceListId);

}
