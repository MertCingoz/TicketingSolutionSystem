package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HALL", schema = "TicketingSolutionDb")
public class Hall {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "NAME")
	private String name;

	@Column(name = "VENUE_ID")
	private BigInteger venueid;

	@Column(name = "ALLOW_OVERLAP_EVENTS")
	private String allowOverlapEvents;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getVenueid() {
		return venueid;
	}

	public void setVenueid(BigInteger venueid) {
		this.venueid = venueid;
	}

	public String getAllowOverlapEvents() {
		return allowOverlapEvents;
	}

	public void setAllowOverlapEvents(String allowOverlapEvents) {
		this.allowOverlapEvents = allowOverlapEvents;
	}

}