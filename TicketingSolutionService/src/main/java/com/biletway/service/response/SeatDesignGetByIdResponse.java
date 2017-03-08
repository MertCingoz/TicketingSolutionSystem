package com.biletway.service.response;

import java.math.BigInteger;

public class SeatDesignGetByIdResponse {

	private BigInteger id;
	private String status;
	private BigInteger seatingPlanDetailId;
	private String rowName;
	private int seatNumber;
	private String rowPattern;
	private int rowOrder;
	private String seatDivs;

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

	public BigInteger getSeatingPlanDetailId() {
		return seatingPlanDetailId;
	}

	public void setSeatingPlanDetailId(BigInteger seatingPlanDetailId) {
		this.seatingPlanDetailId = seatingPlanDetailId;
	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getRowPattern() {
		return rowPattern;
	}

	public void setRowPattern(String rowPattern) {
		this.rowPattern = rowPattern;
	}

	public int getRowOrder() {
		return rowOrder;
	}

	public void setRowOrder(int rowOrder) {
		this.rowOrder = rowOrder;
	}

	public String getSeatDivs() {
		return seatDivs;
	}

	public void setSeatDivs(String seatDivs) {
		this.seatDivs = seatDivs;
	}

}
