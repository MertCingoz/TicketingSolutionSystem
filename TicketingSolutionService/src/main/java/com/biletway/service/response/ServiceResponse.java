package com.biletway.service.response;

public class ServiceResponse {
	String errorCode;
	String errorDesc;
	long serviceDuration;
	Object response;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public long getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(long serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
