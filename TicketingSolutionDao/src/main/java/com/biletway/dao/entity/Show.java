package com.biletway.dao.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOW", schema = "TicketingSolutionDb")
public class Show {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SHOW_TYPE_ID")
	private BigInteger showTypeId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SUMMARY")
	private String summary;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "DURATION")
	private int duration;

	@Column(name = "VENUE_ID")
	private BigInteger venueId;

	@Column(name = "HALL_ID")
	private BigInteger hallId;

	@Column(name = "PRICE_LIST_ID")
	private BigInteger priceListId;

	@Column(name = "MAX_TICKET_SALE_COUNT")
	private int maxTicketSaleCount;

	@Column(name = "SALE_START_DATE")
	private Date saleStartDate;

	@Column(name = "SALE_END_DATE")
	private Date saleEndDate;

	@Column(name = "SEATING_PLAN_ID")
	private BigInteger seatingPlanId;

	@Column(name = "TICKET_COUNT")
	private int ticketCount;

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

	public BigInteger getShowTypeId() {
		return showTypeId;
	}

	public void setShowTypeId(BigInteger showTypeId) {
		this.showTypeId = showTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigInteger getVenueId() {
		return venueId;
	}

	public void setVenueId(BigInteger venueId) {
		this.venueId = venueId;
	}

	public BigInteger getHallId() {
		return hallId;
	}

	public void setHallId(BigInteger hallId) {
		this.hallId = hallId;
	}

	public BigInteger getPriceListId() {
		return priceListId;
	}

	public void setPriceListId(BigInteger priceListId) {
		this.priceListId = priceListId;
	}

	public int getMaxTicketSaleCount() {
		return maxTicketSaleCount;
	}

	public void setMaxTicketSaleCount(int maxTicketSaleCount) {
		this.maxTicketSaleCount = maxTicketSaleCount;
	}

	public Date getSaleStartDate() {
		return saleStartDate;
	}

	public void setSaleStartDate(Date saleStartDate) {
		this.saleStartDate = saleStartDate;
	}

	public Date getSaleEndDate() {
		return saleEndDate;
	}

	public void setSaleEndDate(Date saleEndDate) {
		this.saleEndDate = saleEndDate;
	}

	public BigInteger getSeatingPlanId() {
		return seatingPlanId;
	}

	public void setSeatingPlanId(BigInteger seatingPlanId) {
		this.seatingPlanId = seatingPlanId;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

}
