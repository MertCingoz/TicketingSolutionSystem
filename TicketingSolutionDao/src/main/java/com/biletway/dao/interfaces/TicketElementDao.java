package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.TicketElement;

public interface TicketElementDao {
	TicketElement getById(BigInteger id);

	List<TicketElement> getAllTicketElementsByTicketDesignId(BigInteger ticketDesignId);

	List<TicketElement> searchTicketElementByName(String name);
}
