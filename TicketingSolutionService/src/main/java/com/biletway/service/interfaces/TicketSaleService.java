package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface TicketSaleService {

	ServiceResponse saleTicketByBackOffice(BigInteger showId, BigInteger eventId, String priceTypeIdList,
			String ticketCountList);
}
