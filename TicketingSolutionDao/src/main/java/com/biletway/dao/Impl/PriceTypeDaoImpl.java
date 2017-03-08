package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.PriceType;
import com.biletway.dao.interfaces.PriceTypeDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class PriceTypeDaoImpl extends CommonOperationsDaoImpl implements PriceTypeDao {

	@Override
	public PriceType getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PriceType priceType = (PriceType) session.createCriteria(PriceType.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return priceType;
	}

	@Override
	public List<PriceType> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<PriceType> priceTypeList = (List<PriceType>) session.createCriteria(PriceType.class)
				.add(Restrictions.eqOrIsNull("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return priceTypeList;
	}

	@Override
	public List<PriceType> searchPriceTypeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
