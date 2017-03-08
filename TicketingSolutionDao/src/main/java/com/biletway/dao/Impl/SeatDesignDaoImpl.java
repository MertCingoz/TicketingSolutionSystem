package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.SeatDesign;
import com.biletway.dao.interfaces.SeatDesignDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class SeatDesignDaoImpl implements SeatDesignDao {

	@Override
	public SeatDesign getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SeatDesign seatDesign = (SeatDesign) session.createCriteria(SeatDesign.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return seatDesign;
	}

	@Override
	public List<SeatDesign> getBySeatingPlanDetailId(BigInteger seatingPlanDetailId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<SeatDesign> seatDesignList = session.createCriteria(SeatDesign.class)
				.add(Restrictions.eq("seatingPlanDetailId", seatingPlanDetailId))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).list();
		session.getTransaction().commit();
		return seatDesignList;
	}

}
