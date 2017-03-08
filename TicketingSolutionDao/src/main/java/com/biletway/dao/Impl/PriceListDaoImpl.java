package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.PriceList;
import com.biletway.dao.interfaces.PriceListDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class PriceListDaoImpl extends CommonOperationsDaoImpl implements PriceListDao {

	@Override
	public PriceList getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PriceList priceList = (PriceList) session.createCriteria(PriceList.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return priceList;
	}

	@Override
	public List<PriceList> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<PriceList> priceListList = (List<PriceList>) session.createCriteria(PriceList.class)
				.add(Restrictions.eqOrIsNull("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return priceListList;
	}

	@Override
	public List<PriceList> searchPriceListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
