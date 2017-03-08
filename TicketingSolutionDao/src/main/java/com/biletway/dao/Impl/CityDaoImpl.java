package com.biletway.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.City;
import com.biletway.dao.interfaces.CityDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class CityDaoImpl implements CityDao {

	@Override
	public List<City> getAllList() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<City> cityList = (List<City>) session.createCriteria(City.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).addOrder(Order.asc("showOrder")).list();
		session.getTransaction().commit();
		return cityList;
	}

}
