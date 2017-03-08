package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.biletway.dao.entity.Hall;
import com.biletway.dao.interfaces.HallDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

@Component
public class HallDaoImpl extends CommonOperationsDaoImpl implements HallDao {

	@Override
	public Hall getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Hall hall = (Hall) session.createCriteria(Hall.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return hall;
	}

	@Override
	public List<Hall> getAllHallsByVenueId(BigInteger venueId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Hall> hallList = session.createCriteria(Hall.class).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.add(Restrictions.eq("venueid", venueId)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return hallList;
	}

	@Override
	public List<Hall> searchHallByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
