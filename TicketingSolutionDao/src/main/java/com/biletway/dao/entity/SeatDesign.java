package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEAT_DESIGN")
public class SeatDesign {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SEATING_PLAN_DETAIL_ID")
	private BigInteger seatingPlanDetailId;

	@Column(name = "ROW_NAME")
	private String rowName;

	@Column(name = "SEAT_NUMBER")
	private int seatNumber;

	@Column(name = "ROW_PATTERN")
	private String rowPattern;

	@Column(name = "ROW_ORDER")
	private int rowOrder;

	@Column(name = "SEAT_DIVS")
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
