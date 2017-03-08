package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.TicketDesign;

public class TicketDesignGetAllListResponse {
	List<TicketDesign> ticketDesignList;

	public List<TicketDesign> getTicketDesignList() {
		return ticketDesignList;
	}

	public void setTicketDesignList(List<TicketDesign> ticketDesignList) {
		this.ticketDesignList = ticketDesignList;
	}

}
