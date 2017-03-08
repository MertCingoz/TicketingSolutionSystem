package com.biletway.service.response;

import java.util.List;

import com.biletway.service.types.HomePageEvent;

public class GetHomePageEventsServiceResponse {
	List<HomePageEvent> eventList;

	public List<HomePageEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<HomePageEvent> eventList) {
		this.eventList = eventList;
	}

}
