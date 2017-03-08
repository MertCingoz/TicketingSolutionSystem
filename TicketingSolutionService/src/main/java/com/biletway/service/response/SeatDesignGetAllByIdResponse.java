package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.SeatDesign;

public class SeatDesignGetAllByIdResponse {

	List<SeatDesign> seatDesign;

	public List<SeatDesign> getSeatDesign() {
		return seatDesign;
	}

	public void setSeatDesign(List<SeatDesign> seatDesign) {
		this.seatDesign = seatDesign;
	}

}
