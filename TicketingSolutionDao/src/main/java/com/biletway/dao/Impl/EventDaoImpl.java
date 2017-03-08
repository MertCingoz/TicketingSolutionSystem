package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.Event;
import com.biletway.dao.interfaces.EventDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class EventDaoImpl implements EventDao {

	@Override
	public Event getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event event = (Event) session.createCriteria(Event.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return event;
	}

	@Override
	public List<Event> getAllEventsByShowId(BigInteger showId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Event> eventList = session.createCriteria(Event.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).add(Restrictions.eq("showId", showId))
				.list();
		session.getTransaction().commit();
		return eventList;
	}

	@Override
	public List<Event> searchEventByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
