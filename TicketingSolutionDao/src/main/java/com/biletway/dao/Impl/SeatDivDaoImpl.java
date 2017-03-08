package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.SeatDiv;
import com.biletway.dao.interfaces.SeatDivDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class SeatDivDaoImpl implements SeatDivDao {

	@Override
	public SeatDiv getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SeatDiv seatDiv = (SeatDiv) session.createCriteria(SeatDiv.class).add(Restrictions.eq("id", id)).uniqueResult();
		session.getTransaction().commit();
		return seatDiv;
	}

	@Override
	public List<SeatDiv> getBySeatDesignId(BigInteger seatDesignId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<SeatDiv> seatDivList = session.createCriteria(SeatDiv.class)
				.add(Restrictions.eq("seatDesignId", seatDesignId))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).list();
		session.getTransaction().commit();
		return seatDivList;
	}

}
