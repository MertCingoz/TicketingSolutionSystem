package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.TicketType;

public interface TicketTypeDao {
	TicketType getById(BigInteger id);

	List<TicketType> getAllList();

	List<TicketType> searchTicketTypeByName(String name);
}
