package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.Venue;
import com.biletway.dao.interfaces.VenueDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class VenueDaoImpl extends CommonOperationsDaoImpl implements VenueDao {

	@Override
	public Venue getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Venue venue = (Venue) session.createCriteria(Venue.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return venue;
	}

	@Override
	public List<Venue> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Venue> venueList = (List<Venue>) session.createCriteria(Venue.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return venueList;
	}

	@Override
	public List<Venue> searchVenueByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
