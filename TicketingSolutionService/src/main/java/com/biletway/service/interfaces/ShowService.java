package com.biletway.service.interfaces;

import java.math.BigInteger;
import java.util.Date;

import com.biletway.service.response.ServiceResponse;

public interface ShowService {
	ServiceResponse insert(BigInteger showTypeId, String name, String summary, String description, Date startDate,
			Date endDate, int duration, BigInteger venueId, BigInteger hallId, BigInteger priceListId,
			int maxTicketSaleCount, Date saleStartDate, Date saleEndDate, String paymentMethods,
			BigInteger seatingPlanId,int ticketCount);

	ServiceResponse update(BigInteger id, BigInteger showTypeId, String name, String summary, String description,
			Date startDate, Date endDate, int duration, BigInteger venueId, BigInteger hallId, BigInteger priceListId,
			int maxTicketSaleCount, Date saleStartDate, Date saleEndDate, String paymentMethods,
			BigInteger seatingPlanId,int ticketCount);

	ServiceResponse delete(BigInteger id);

	ServiceResponse getShowById(BigInteger id);

	ServiceResponse getShowByIdToCreateEvent(BigInteger id);

	ServiceResponse getAllShows();
}
