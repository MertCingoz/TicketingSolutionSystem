package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.TicketType;

public class TicketTypeGetAllListResponse {
	List<TicketType> ticketTypeList;

	public List<TicketType> getTicketTypeList() {
		return ticketTypeList;
	}

	public void setTicketTypeList(List<TicketType> ticketTypeList) {
		this.ticketTypeList = ticketTypeList;
	}

}
