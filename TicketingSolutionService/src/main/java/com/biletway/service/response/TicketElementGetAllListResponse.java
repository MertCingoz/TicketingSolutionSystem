package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.TicketElement;

public class TicketElementGetAllListResponse {
	List<TicketElement> ticketElementList;

	public List<TicketElement> getTicketElementList() {
		return ticketElementList;
	}

	public void setTicketElementList(List<TicketElement> ticketElementList) {
		this.ticketElementList = ticketElementList;
	}

}
