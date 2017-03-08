package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.HallBlock;

public interface HallBlockDao {
	HallBlock getById(BigInteger id);

	List<HallBlock> getAllHallBlocksByHallId(BigInteger hallId);

	List<HallBlock> searchHallByName(String name);

}
