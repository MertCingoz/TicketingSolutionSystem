package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.SeatCategory;

public class SeatCategoryGetAllListResponse {
	List<SeatCategory> seatCategoryList;

	public List<SeatCategory> getSeatCategoryList() {
		return seatCategoryList;
	}

	public void setSeatCategoryList(List<SeatCategory> seatCategoryList) {
		this.seatCategoryList = seatCategoryList;
	}

}
