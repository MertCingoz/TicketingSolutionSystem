package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.TicketDesign;

public interface TicketDesignDao {
	TicketDesign getById(BigInteger id);

	List<TicketDesign> getAllList();

	List<TicketDesign> searchTicketDesignByName(String name);
}
