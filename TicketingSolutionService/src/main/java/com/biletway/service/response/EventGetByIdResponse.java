package com.biletway.service.response;

import java.math.BigInteger;

public class EventGetByIdResponse {

	private BigInteger id;
	private String status;
	private BigInteger showId;
	private String eventDate;
	private int duration;
	private int selectSeat;
	private int saleStatus;
	private BigInteger ticketDesignId;
	private String createDate;
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

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public BigInteger getCreateUser() {
		return createUser;
	}

	public void setCreateUser(BigInteger createUser) {
		this.createUser = createUser;
	}

}
