package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.Show;

public interface ShowDao {

	Show getById(BigInteger id);

	List<Show> getAllShows();

}
