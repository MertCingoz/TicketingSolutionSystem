package com.biletway.service.types;

import java.util.List;

import com.biletway.dao.entity.MainMenu;

public class MenuTree {
	MainMenu mainMenu;
	List<MainMenu> branches;

	public MainMenu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public List<MainMenu> getBranches() {
		return branches;
	}

	public void setBranches(List<MainMenu> branches) {
		this.branches = branches;
	}

}
