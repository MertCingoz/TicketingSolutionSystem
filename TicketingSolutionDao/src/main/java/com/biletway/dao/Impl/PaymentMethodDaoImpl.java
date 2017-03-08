package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.PaymentMethod;
import com.biletway.dao.interfaces.PaymentMethodDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class PaymentMethodDaoImpl extends CommonOperationsDaoImpl implements PaymentMethodDao {

	@Override
	public PaymentMethod getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PaymentMethod paymentMethod = (PaymentMethod) session.createCriteria(PaymentMethod.class)
				.add(Restrictions.eq("id", id)).add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.uniqueResult();
		session.getTransaction().commit();
		return paymentMethod;
	}

	@Override
	public List<PaymentMethod> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<PaymentMethod> paymentMethodList = (List<PaymentMethod>) session.createCriteria(PaymentMethod.class)
				.add(Restrictions.eqOrIsNull("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return paymentMethodList;
	}

}
