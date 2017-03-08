package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.MainMenu;
import com.biletway.dao.interfaces.MenuDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class MenuDaoImpl implements MenuDao {

	@Override
	public List<MainMenu> getRootMenuList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<MainMenu> mainMenuList = session.createCriteria(MainMenu.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.add(Restrictions.eqOrIsNull("root", new BigInteger("0"))).list();
		session.getTransaction().commit();
		return mainMenuList;
	}

	@Override
	public List<MainMenu> getBranchList(BigInteger rootId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<MainMenu> branchList = session.createCriteria(MainMenu.class).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.add(Restrictions.eqOrIsNull("root", rootId)).list();
		session.getTransaction().commit();
		return branchList;
	}

}
