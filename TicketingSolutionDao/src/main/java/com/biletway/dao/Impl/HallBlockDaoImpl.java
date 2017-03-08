package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.biletway.dao.entity.HallBlock;
import com.biletway.dao.interfaces.HallBlockDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

@Component
public class HallBlockDaoImpl extends CommonOperationsDaoImpl implements HallBlockDao {

	@Override
	public HallBlock getById(BigInteger id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		HallBlock hallBlock = (HallBlock) session.createCriteria(HallBlock.class).add(Restrictions.eq("id", id))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return hallBlock;
	}

	@Override
	public List<HallBlock> getAllHallBlocksByHallId(BigInteger hallId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<HallBlock> hallBlockList = session.createCriteria(HallBlock.class)
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).add(Restrictions.eq("hallId", hallId))
				.addOrder(Order.asc("name")).list();
		session.getTransaction().commit();
		return hallBlockList;
	}

	@Override
	public List<HallBlock> searchHallByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
