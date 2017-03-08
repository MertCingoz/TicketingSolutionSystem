package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SEATING_PLAN_DETAIL")
public class SeatingPlanDetail {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SEATING_PLAN_ID")
	private BigInteger seatingPlanId;

	@Column(name = "HALL_BLOCK_ID")
	private BigInteger hallBlockId;

	@Column(name = "HALL_BLOCK_CAPACITY")
	private int hallBlockCapacity;

	@Column(name = "HAS_SEATING_PLAN")
	private String hasSeatingPlan;

	@Column(name = "DRAWING_AREA")
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
		if (hasSeatingPlan.equals("true")) {
			this.hasSeatingPlan = "Y";
		} else if (hasSeatingPlan.equals("false")) {
			this.hasSeatingPlan = "N";
		} else {
			this.hasSeatingPlan = hasSeatingPlan;
		}
	}

	public String getDrawingArea() {
		return drawingArea;
	}

	public void setDrawingArea(String drawingArea) {
		this.drawingArea = drawingArea;
	}

}
