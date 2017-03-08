package com.biletway.service.response;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.ShowPaymentMethod;

public class ShowGetByIdResponse {

	private BigInteger id;
	private String status;
	private BigInteger showTypeId;
	private String name;
	private String summary;
	private String description;
	private String startDate;
	private String endDate;
	private int duration;
	private BigInteger venueId;
	private BigInteger hallId;
	private BigInteger priceListId;
	private int maxTicketSaleCount;
	private String saleStartDate;
	private String saleEndDate;
	private BigInteger seatingPlanId;
	private List<ShowPaymentMethod> showPaymentMethods;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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

	public String getSaleStartDate() {
		return saleStartDate;
	}

	public void setSaleStartDate(String saleStartDate) {
		this.saleStartDate = saleStartDate;
	}

	public String getSaleEndDate() {
		return saleEndDate;
	}

	public void setSaleEndDate(String saleEndDate) {
		this.saleEndDate = saleEndDate;
	}

	public BigInteger getSeatingPlanId() {
		return seatingPlanId;
	}

	public void setSeatingPlanId(BigInteger seatingPlanId) {
		this.seatingPlanId = seatingPlanId;
	}

	public List<ShowPaymentMethod> getShowPaymentMethods() {
		return showPaymentMethods;
	}

	public void setShowPaymentMethods(List<ShowPaymentMethod> showPaymentMethods) {
		this.showPaymentMethods = showPaymentMethods;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}

}
