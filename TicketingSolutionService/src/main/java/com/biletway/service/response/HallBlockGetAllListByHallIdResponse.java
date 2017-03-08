package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.HallBlock;

public class HallBlockGetAllListByHallIdResponse {
	List<HallBlock> hallBlockList;

	public List<HallBlock> getHallBlockList() {
		return hallBlockList;
	}

	public void setHallBlockList(List<HallBlock> hallBlockList) {
		this.hallBlockList = hallBlockList;
	}

}
