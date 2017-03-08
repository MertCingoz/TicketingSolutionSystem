package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.ShowType;

public interface ShowTypeDao {
	ShowType getById(BigInteger id);

	List<ShowType> getAllList();

	List<ShowType> searchShowTypeByName(String name);
}
