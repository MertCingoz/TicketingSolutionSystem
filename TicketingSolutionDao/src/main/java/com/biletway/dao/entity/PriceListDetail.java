package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRICE_LIST_DETAIL", schema = "TicketingSolutionDb")
public class PriceListDetail {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "PRICE_LIST_ID")
	private BigInteger priceListId;

	@Column(name = "PRICE_TYPE_ID")
	private BigInteger priceTypeId;

	@Column(name = "SEAT_CATEGORY_ID")
	private BigInteger seatCategoryId;

	@Column(name = "PRICE")
	private double price;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigInteger getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(BigInteger priceListId) {
		this.priceListId = priceListId;
	}

	public BigInteger getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(BigInteger priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	public BigInteger getSeatCategoryId() {
		return seatCategoryId;
	}

	public void setSeatCategoryId(BigInteger seatCategoryId) {
		this.seatCategoryId = seatCategoryId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
