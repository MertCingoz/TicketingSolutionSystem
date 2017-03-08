package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.TicketDesign;
import com.biletway.dao.interfaces.TicketDesignDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class TicketDesignDaoImpl implements TicketDesignDao {

	@Override
	public TicketDesign getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TicketDesign ticketDesign = (TicketDesign) session.createCriteria(TicketDesign.class)
				.add(Restrictions.eq("id", id)).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.uniqueResult();
		session.getTransaction().commit();
		return ticketDesign;
	}

	@Override
	public List<TicketDesign> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<TicketDesign> ticketDesignList = (List<TicketDesign>) session.createCriteria(TicketDesign.class)
				.add(Restrictions.eqOrIsNull("status", DaoConstants.ACTIVE_STATUS)).list();
		session.getTransaction().commit();
		return ticketDesignList;
	}

	@Override
	public List<TicketDesign> searchTicketDesignByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
