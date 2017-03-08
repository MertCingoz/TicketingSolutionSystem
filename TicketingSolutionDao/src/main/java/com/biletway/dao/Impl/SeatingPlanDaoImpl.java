package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.SeatingPlan;
import com.biletway.dao.interfaces.SeatingPlanDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class SeatingPlanDaoImpl implements SeatingPlanDao {

	@Override
	public SeatingPlan getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SeatingPlan seatingPlan = (SeatingPlan) session.createCriteria(SeatingPlan.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		session.getTransaction().commit();
		return seatingPlan;
	}

	@Override
	public List<SeatingPlan> searchSeatingPlanByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SeatingPlan> getAllByHallId(BigInteger hallId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<SeatingPlan> seatingPlanList = session.createCriteria(SeatingPlan.class)
				.add(Restrictions.eq("hallId", hallId)).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.list();
		session.getTransaction().commit();
		return seatingPlanList;
	}

}
