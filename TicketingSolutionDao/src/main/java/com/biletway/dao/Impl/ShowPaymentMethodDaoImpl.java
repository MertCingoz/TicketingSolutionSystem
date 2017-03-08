package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.ShowPaymentMethod;
import com.biletway.dao.interfaces.ShowPaymentMethodDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class ShowPaymentMethodDaoImpl implements ShowPaymentMethodDao {

	@Override
	public List<ShowPaymentMethod> getPaymentMethodsByShowId(BigInteger showId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<ShowPaymentMethod> showPaymentMethodList = (List<ShowPaymentMethod>) session
				.createCriteria(ShowPaymentMethod.class)
				.add(Restrictions.eqOrIsNull("status", DaoConstants.ACTIVE_STATUS))
				.add(Restrictions.eqOrIsNull("showId", showId)).list();
		session.getTransaction().commit();
		return showPaymentMethodList;
	}

	@Override
	public ShowPaymentMethod getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ShowPaymentMethod showPaymentMethod = (ShowPaymentMethod) session.createCriteria(ShowPaymentMethod.class)
				.add(Restrictions.eq("id", id)).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.uniqueResult();
		session.getTransaction().commit();
		return showPaymentMethod;
	}

}
