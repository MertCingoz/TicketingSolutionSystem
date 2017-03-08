package com.biletway.dao.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENERAL_ERROR_LOG")
public class GeneralErrorLog {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "OPERATION_TYPE")
	private String operationType;

	@Column(name = "ERROR_CLASS")
	private String errorClass;

	@Column(name = "ERROR_DESCRIPTION")
	private String errorDescription;

	@Column(name = "ERROR_TIME")
	private Date errorTime;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getErrorClass() {
		return errorClass;
	}

	public void setErrorClass(String errorClass) {
		this.errorClass = errorClass;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Date getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}

}
