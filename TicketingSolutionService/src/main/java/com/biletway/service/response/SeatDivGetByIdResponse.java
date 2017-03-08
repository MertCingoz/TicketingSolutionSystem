package com.biletway.service.response;

import java.math.BigInteger;

public class SeatDivGetByIdResponse {

	private BigInteger id;
	private String status;
	private BigInteger seatDesignId;
	private String divId;
	private String seatType;
	private String seatCategory;

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

	public BigInteger getSeatDesignId() {
		return seatDesignId;
	}

	public void setSeatDesignId(BigInteger seatDesignId) {
		this.seatDesignId = seatDesignId;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getSeatCategory() {
		return seatCategory;
	}

	public void setSeatCategory(String seatCategory) {
		this.seatCategory = seatCategory;
	}

}
