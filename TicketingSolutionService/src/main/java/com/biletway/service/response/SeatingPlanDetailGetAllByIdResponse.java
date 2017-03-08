package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.SeatingPlanDetail;

public class SeatingPlanDetailGetAllByIdResponse {

	List<SeatingPlanDetail> seatingPlanDetailList;

	public List<SeatingPlanDetail> getSeatingPlanDetailList() {
		return seatingPlanDetailList;
	}

	public void setSeatingPlanDetailList(List<SeatingPlanDetail> seatingPlanDetailList) {
		this.seatingPlanDetailList = seatingPlanDetailList;
	}

}
