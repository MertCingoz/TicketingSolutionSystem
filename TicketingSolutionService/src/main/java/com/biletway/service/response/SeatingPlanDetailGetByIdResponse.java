package com.biletway.service.response;

import java.math.BigInteger;

public class SeatingPlanDetailGetByIdResponse {

	private BigInteger id;

	private String status;
	private BigInteger seatingPlanId;
	private BigInteger hallBlockId;
	private int hallBlockCapacity;
	private String hasSeatingPlan;
	private String drawingArea;

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

	public BigInteger getSeatingPlanId() {
		return seatingPlanId;
	}

	public void setSeatingPlanId(BigInteger seatingPlanId) {
		this.seatingPlanId = seatingPlanId;
	}

	public BigInteger getHallBlockId() {
		return hallBlockId;
	}

	public void setHallBlockId(BigInteger hallBlockId) {
		this.hallBlockId = hallBlockId;
	}

	public int getHallBlockCapacity() {
		return hallBlockCapacity;
	}

	public void setHallBlockCapacity(int hallBlockCapacity) {
		this.hallBlockCapacity = hallBlockCapacity;
	}

	public String getHasSeatingPlan() {
		return hasSeatingPlan;
	}

	public void setHasSeatingPlan(String hasSeatingPlan) {
		this.hasSeatingPlan = hasSeatingPlan;
	}

	public String getDrawingArea() {
		return drawingArea;
	}

	public void setDrawingArea(String drawingArea) {
		this.drawingArea = drawingArea;
	}

}
