package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TICKET_POOL", schema = "TicketingSolutionDb")

public class TicketPool {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "EVENT_ID")
	private BigInteger eventId;

	@Column(name = "SEAT_DESIGN_ID")
	private BigInteger seatDesignId;

	@Column(name = "BARCODE")
	private String barcode;

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

	public BigInteger getEventId() {
		return eventId;
	}

	public void setEventId(BigInteger eventId) {
		this.eventId = eventId;
	}

	public BigInteger getSeatDesignId() {
		return seatDesignId;
	}

	public void setSeatDesignId(BigInteger seatDesignId) {
		this.seatDesignId = seatDesignId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
