package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.SeatCategory;
import com.biletway.dao.interfaces.SeatCategoryDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class SeatCategoryDaoImpl extends CommonOperationsDaoImpl implements SeatCategoryDao {

	@Override
	public SeatCategory getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SeatCategory seatCategory = (SeatCategory) session.createCriteria(SeatCategory.class)
				.add(Restrictions.eq("id", id)).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.uniqueResult();
		session.getTransaction().commit();
		return seatCategory;
	}

	@Override
	public List<SeatCategory> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<SeatCategory> seatCategoryList = (List<SeatCategory>) session.createCriteria(SeatCategory.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return seatCategoryList;
	}

	@Override
	public List<SeatCategory> searchSeatCategoryByName(String name) {
		return null;
	}

}
