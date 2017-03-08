package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.Hall;

public class HallGetAllListByVenueIdResponse {
	List<Hall> hallList;

	public List<Hall> getHallList() {
		return hallList;
	}

	public void setHallList(List<Hall> hallList) {
		this.hallList = hallList;
	}

}
