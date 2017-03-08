package com.biletway.rest.resonse;

public class RestResponse {
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

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public long getServiceDuration() {
		return serviceDuration;
	}

	public void setServiceDuration(long serviceDuration) {
		this.serviceDuration = serviceDuration;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("errorCode=");
		str.append(errorCode);
		str.append("|");
		str.append("errorDesc=");
		str.append(errorDesc);
		str.append("|");
		return str.toString();
	}

	public RestResponse() {
		errorCode="";
		errorDesc="";
		serviceDuration = 1;
	}
}
