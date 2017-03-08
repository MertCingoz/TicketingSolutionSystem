package com.biletway.service.response;

import java.math.BigInteger;

public class SeatingPlanGetByIdResponse {

	private BigInteger id;

	private String status;

	private String name;

	private BigInteger hallId;

	private String background;

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

	public BigInteger getHallId() {
		return hallId;
	}

	public void setHallId(BigInteger hallId) {
		this.hallId = hallId;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

}
