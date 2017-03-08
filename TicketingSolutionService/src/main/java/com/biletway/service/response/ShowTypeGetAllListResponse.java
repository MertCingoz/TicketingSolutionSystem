package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.ShowType;

public class ShowTypeGetAllListResponse {
	List<ShowType> showTypeList;

	public List<ShowType> getShowTypeList() {
		return showTypeList;
	}

	public void setShowTypeList(List<ShowType> showTypeList) {
		this.showTypeList = showTypeList;
	}

}
