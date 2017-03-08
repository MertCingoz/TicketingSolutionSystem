package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.City;

public class CityGetAllListResponse {
	List<City> cityList;

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

}
