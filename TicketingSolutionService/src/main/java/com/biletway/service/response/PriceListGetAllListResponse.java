package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.PriceList;

public class PriceListGetAllListResponse {
	List<PriceList> priceListList;

	public List<PriceList> getPriceListList() {
		return priceListList;
	}

	public void setPriceTypeList(List<PriceList> priceListList) {
		this.priceListList = priceListList;
	}

}
