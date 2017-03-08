package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.ShowType;
import com.biletway.dao.interfaces.ShowTypeDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class ShowTypeDaoImpl extends CommonOperationsDaoImpl implements ShowTypeDao {

	@Override
	public List<ShowType> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<ShowType> showTypeList = (List<ShowType>) session.createCriteria(ShowType.class)
				.add(Restrictions.eqOrIsNull("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return showTypeList;
	}

	@Override
	public ShowType getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ShowType showType = (ShowType) session.createCriteria(ShowType.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return showType;
	}

	@Override
	public List<ShowType> searchShowTypeByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
