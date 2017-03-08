package com.biletway.service.interfaces;

import java.math.BigInteger;

import com.biletway.service.response.ServiceResponse;

public interface TicketPoolService {

	ServiceResponse generateTicketBarcodeForEvent(BigInteger eventId,BigInteger showId);

}
