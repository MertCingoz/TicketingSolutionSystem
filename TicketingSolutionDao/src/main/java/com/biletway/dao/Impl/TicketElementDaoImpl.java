package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.TicketElement;
import com.biletway.dao.interfaces.TicketElementDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class TicketElementDaoImpl implements TicketElementDao {

	@Override
	public TicketElement getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TicketElement ticketElement = (TicketElement) session.createCriteria(TicketElement.class)
				.add(Restrictions.eq("id", id)).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.uniqueResult();
		session.getTransaction().commit();
		return ticketElement;
	}

	@Override
	public List<TicketElement> getAllTicketElementsByTicketDesignId(BigInteger ticketDesignId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<TicketElement> ticketElementList = (List<TicketElement>) session.createCriteria(TicketElement.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.add(Restrictions.eq("ticketDesignId", ticketDesignId)).list();
		session.getTransaction().commit();
		return ticketElementList;
	}

	@Override
	public List<TicketElement> searchTicketElementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
