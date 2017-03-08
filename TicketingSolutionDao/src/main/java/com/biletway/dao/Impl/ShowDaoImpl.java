package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.Show;
import com.biletway.dao.interfaces.ShowDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class ShowDaoImpl implements ShowDao {

	@Override
	public Show getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Show show = (Show) session.createCriteria(Show.class).add(Restrictions.eq("id", id)).uniqueResult();
		session.getTransaction().commit();
		return show;
	}

	@Override
	public List<Show> getAllShows() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Show> showList = session.createCriteria(Show.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).list();
		session.getTransaction().commit();
		return showList;
	}

}
