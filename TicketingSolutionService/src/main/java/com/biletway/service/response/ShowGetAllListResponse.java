package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.Show;

public class ShowGetAllListResponse {
	List<Show> showList;

	public List<Show> getShowList() {
		return showList;
	}

	public void setShowList(List<Show> showList) {
		this.showList = showList;
	}

}
