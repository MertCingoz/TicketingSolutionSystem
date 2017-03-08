package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APPLICATION_SETTINGS", schema = "TicketingSolutionDb")
public class ApplicationSettings {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "KEY")
	private String key;

	@Column(name = "VALUE")
	private String value;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
