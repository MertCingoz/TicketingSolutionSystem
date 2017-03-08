package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.Venue;

public class VenueGetAllListResponse {
	List<Venue> venueList;

	public List<Venue> getVenueList() {
		return venueList;
	}

	public void setVenueList(List<Venue> venueList) {
		this.venueList = venueList;
	}

}
