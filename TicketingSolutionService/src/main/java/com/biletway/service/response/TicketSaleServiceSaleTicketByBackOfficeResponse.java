package com.biletway.service.response;

import java.util.List;

public class TicketSaleServiceSaleTicketByBackOfficeResponse {

	boolean isSuccess;

	List<byte[]> ticketPdfList;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public List<byte[]> getTicketPdfList() {
		return ticketPdfList;
	}

	public void setTicketPdfList(List<byte[]> ticketPdfList) {
		this.ticketPdfList = ticketPdfList;
	}

}
