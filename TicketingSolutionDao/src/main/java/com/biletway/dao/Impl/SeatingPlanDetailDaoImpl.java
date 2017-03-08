package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.SeatingPlanDetail;
import com.biletway.dao.interfaces.SeatingPlanDetailDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class SeatingPlanDetailDaoImpl implements SeatingPlanDetailDao {

	@Override
	public SeatingPlanDetail getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SeatingPlanDetail seatingPlanDetail = (SeatingPlanDetail) session.createCriteria(SeatingPlanDetail.class)
				.add(Restrictions.eq("id", id)).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.uniqueResult();
		session.getTransaction().commit();
		return seatingPlanDetail;
	}

	@Override
	public List<SeatingPlanDetail> getBySeatingPlanId(BigInteger seatingPlanId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<SeatingPlanDetail> seatingPlanDetailList = session.createCriteria(SeatingPlanDetail.class)
				.add(Restrictions.eq("seatingPlanId", seatingPlanId))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).list();
		session.getTransaction().commit();
		return seatingPlanDetailList;
	}

}
