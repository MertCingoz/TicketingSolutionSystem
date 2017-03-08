package com.biletway.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.biletway.dao.entity.MainMenu;
import com.biletway.dao.interfaces.MenuDao;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.MenuService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.types.MenuTree;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceHelper;

public class MenuServiceImpl implements MenuService {
	MenuDao menuDao = (MenuDao) ApplicationContextUtil.getApplicationContext().getBean("menuDao");
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;

	@Override
	public ServiceResponse getMenuTree(String userId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();

		List<MenuTree> menuTreeList = new ArrayList<MenuTree>();

		List<MainMenu> mainMenuList = menuDao.getRootMenuList();
		MenuTree menuTree = new MenuTree();

		for (int i = 0; i < mainMenuList.size(); i++) {
			List<MainMenu> branches = new ArrayList<MainMenu>();
			menuTree = new MenuTree();
			menuTree.setMainMenu(mainMenuList.get(i));
			List<MainMenu> branchList = menuDao.getBranchList(mainMenuList.get(i).getId());

			for (int j = 0; j < branchList.size(); j++) {
				branches.add(branchList.get(j));
			}
			menuTree.setBranches(branches);
			menuTreeList.add(menuTree);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, menuTreeList);
	}

}
