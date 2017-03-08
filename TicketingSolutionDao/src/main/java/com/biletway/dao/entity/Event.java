package com.biletway.dao.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT", schema = "TicketingSolutionDb")
public class Event {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SHOW_ID")
	private BigInteger showId;

	@Column(name = "EVENT_DATE")
	private Date eventDate;

	@Column(name = "DURATION")
	private int duration;

	@Column(name = "SELECT_SEAT")
	private int selectSeat;

	@Column(name = "SALE_STATUS")
	private int saleStatus;

	@Column(name = "TICKET_DESIGN_ID")
	private BigInteger ticketDesignId;

	@Column(name = "BARCODE_CREATION_STATUS")
	private String barcodeCreationStatus;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER")
	private BigInteger createUser;

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

	public BigInteger getShowId() {
		return showId;
	}

	public void setShowId(BigInteger showId) {
		this.showId = showId;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSelectSeat() {
		return selectSeat;
	}

	public void setSelectSeat(int selectSeat) {
		this.selectSeat = selectSeat;
	}

	public int getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(int saleStatus) {
		this.saleStatus = saleStatus;
	}

	public BigInteger getTicketDesignId() {
		return ticketDesignId;
	}

	public void setTicketDesignId(BigInteger ticketDesignId) {
		this.ticketDesignId = ticketDesignId;
	}

	public String getBarcodeCreationStatus() {
		return barcodeCreationStatus;
	}

	public void setBarcodeCreationStatus(String barcodeCreationStatus) {
		this.barcodeCreationStatus = barcodeCreationStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigInteger getCreateUser() {
		return createUser;
	}

	public void setCreateUser(BigInteger createUser) {
		this.createUser = createUser;
	}

}
