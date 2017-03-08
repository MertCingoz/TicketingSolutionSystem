package com.biletway.service.response;

import java.util.List;

import com.biletway.dao.entity.Event;

public class EventGetAllListByShowIdResponse {
	List<Event> eventList;

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

}
