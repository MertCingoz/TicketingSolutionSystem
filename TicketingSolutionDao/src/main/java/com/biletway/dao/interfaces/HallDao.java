package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.Hall;

public interface HallDao {

	Hall getById(BigInteger id);

	List<Hall> getAllHallsByVenueId(BigInteger venueId);

	List<Hall> searchHallByName(String name);

}
