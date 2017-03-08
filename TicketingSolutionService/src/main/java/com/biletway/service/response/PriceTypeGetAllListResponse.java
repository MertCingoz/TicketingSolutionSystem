package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.PriceType;

public class PriceTypeGetAllListResponse {
	List<PriceType> priceTypeList;

	public List<PriceType> getPriceTypeList() {
		return priceTypeList;
	}

	public void setPriceTypeList(List<PriceType> priceTypeList) {
		this.priceTypeList = priceTypeList;
	}

}
