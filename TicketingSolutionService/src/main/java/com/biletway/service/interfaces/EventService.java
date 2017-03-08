package com.biletway.service.interfaces;

import java.math.BigInteger;
import java.util.Date;

import com.biletway.service.response.ServiceResponse;

public interface EventService {

	ServiceResponse insert(BigInteger showId, Date eventDate, int duration, int selectSeat, int saleStatus,
			BigInteger ticketDesignId, Date createDate, BigInteger createUser);

	ServiceResponse update(BigInteger id, Date eventDate, int duration, int selectSeat, int saleStatus,
			BigInteger ticketDesignId);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getEventById(BigInteger id);

	ServiceResponse getAllEventsByShowId(BigInteger showId);
	
	ServiceResponse getHomePageEvents();

}