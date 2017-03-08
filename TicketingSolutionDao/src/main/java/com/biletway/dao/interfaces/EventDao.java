package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.Event;

public interface EventDao {

	Event getById(BigInteger id);

	List<Event> getAllEventsByShowId(BigInteger showId);

	List<Event> searchEventByName(String name);

}
