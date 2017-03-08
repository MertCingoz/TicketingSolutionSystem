package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOW_PAYMENT_METHOD", schema = "TicketingSolutionDb")
public class ShowPaymentMethod {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SHOW_ID")
	private BigInteger showId;

	@Column(name = "PAYMENT_METHOD_ID")
	private BigInteger paymentMethodId;

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

	public BigInteger getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(BigInteger paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

}
