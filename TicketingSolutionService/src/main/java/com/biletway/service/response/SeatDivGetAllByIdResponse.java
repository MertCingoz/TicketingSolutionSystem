package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.SeatDiv;

public class SeatDivGetAllByIdResponse {

	List<SeatDiv> seatDiv;

	public List<SeatDiv> getSeatDiv() {
		return seatDiv;
	}

	public void setSeatDiv(List<SeatDiv> seatDiv) {
		this.seatDiv = seatDiv;
	}

}
