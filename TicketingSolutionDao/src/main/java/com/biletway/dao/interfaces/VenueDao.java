package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.Venue;

public interface VenueDao {
	Venue getById(BigInteger id);

	List<Venue> getAllList();

	List<Venue> searchVenueByName(String name);
}
