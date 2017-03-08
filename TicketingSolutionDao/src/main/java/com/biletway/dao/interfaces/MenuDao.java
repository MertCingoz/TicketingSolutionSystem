package com.biletway.dao.interfaces;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.MainMenu;

public interface MenuDao {
	List<MainMenu> getRootMenuList();

	List<MainMenu> getBranchList(BigInteger rootId);
}
