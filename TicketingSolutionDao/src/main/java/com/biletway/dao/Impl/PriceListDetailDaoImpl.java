package com.biletway.dao.Impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.PriceListDetail;
import com.biletway.dao.interfaces.PriceListDetailDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.dao.util.HibernateUtil;

public class PriceListDetailDaoImpl extends CommonOperationsDaoImpl implements PriceListDetailDao {

	@Override
	public List<PriceListDetail> getPriceListDetailList(BigInteger priceListId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PriceListDetail> priceListDetailList = session.createCriteria(PriceListDetail.class)
				.add(Restrictions.eq("priceListId", priceListId))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).list();
		session.getTransaction().commit();
		return priceListDetailList;
	}

	@Override
	public PriceListDetail getPriceListDetail(BigInteger priceListId, BigInteger seatCategoryId,
			BigInteger priceTypeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		PriceListDetail priceListDetail = (PriceListDetail) session.createCriteria(PriceListDetail.class)
				.add(Restrictions.eq("priceListId", priceListId)).add(Restrictions.eq("seatCategoryId", seatCategoryId))
				.add(Restrictions.eq("priceTypeId", priceTypeId))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS)).uniqueResult();
		session.getTransaction().commit();
		return priceListDetail;
	}

	@Override
	public List<PriceListDetail> getPricedPriceListDetail(BigInteger priceListId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<PriceListDetail> priceListDetailList = session.createCriteria(PriceListDetail.class)
				.add(Restrictions.eq("priceListId", priceListId))
				.add(Restrictions.eq("status", DaoConstants.ACTIVE_STATUS))
				.add(Restrictions.gt("price", new Double("0"))).list();
		session.getTransaction().commit();
		return priceListDetailList;
	}

}
