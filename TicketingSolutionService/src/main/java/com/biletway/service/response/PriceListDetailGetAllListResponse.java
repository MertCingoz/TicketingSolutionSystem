package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.PriceListDetail;

public class PriceListDetailGetAllListResponse {
	List<PriceListDetail> priceListDetailList;

	public List<PriceListDetail> getPriceListDetailList() {
		return priceListDetailList;
	}

	public void setPriceListDetailList(List<PriceListDetail> priceListDetailList) {
		this.priceListDetailList = priceListDetailList;
	}

}
