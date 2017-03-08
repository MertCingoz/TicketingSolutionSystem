package com.biletway.service.response;

import java.math.BigInteger;

public class PriceListDetailForSaleScreen {

	BigInteger priceTypeId;
	String name;
	Double price;

	public BigInteger getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(BigInteger priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
