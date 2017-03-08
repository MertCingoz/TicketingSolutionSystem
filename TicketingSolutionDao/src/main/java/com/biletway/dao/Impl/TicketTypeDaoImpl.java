package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.TicketType;
import com.biletway.dao.interfaces.TicketTypeDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class TicketTypeDaoImpl extends CommonOperationsDaoImpl implements TicketTypeDao {

	@Override
	public TicketType getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TicketType ticketType = (TicketType) session.createCriteria(TicketType.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return ticketType;
	}

	@Override
	public List<TicketType> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<TicketType> ticketTypeList = (List<TicketType>) session.createCriteria(TicketType.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return ticketTypeList;
	}

	@Override
	public List<TicketType> searchTicketTypeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
