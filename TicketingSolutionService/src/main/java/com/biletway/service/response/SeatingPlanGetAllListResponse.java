package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.SeatingPlan;

public class SeatingPlanGetAllListResponse {
	List<SeatingPlan> seatingPlanList;

	public List<SeatingPlan> getSeatingPlanList() {
		return seatingPlanList;
	}

	public void setSeatingPlanList(List<SeatingPlan> seatingPlanList) {
		this.seatingPlanList = seatingPlanList;
	}

}
