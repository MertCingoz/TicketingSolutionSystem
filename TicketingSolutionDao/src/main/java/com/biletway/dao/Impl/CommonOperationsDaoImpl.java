package com.biletway.dao.Impl;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.util.HibernateUtil;
import com.biletway.dao.util.LogOperations;

@Component
public class CommonOperationsDaoImpl implements CommonOperationsDao {

	@Override
	public boolean insert(Object entity) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LogOperations.getInstance().createGeneralErrorLog("INSERT", entity.getClass().getName().toString(),
					e.getCause().toString());
			return false;
		}
		return true;
	}

	@Override
	public Object insertAndGetEntity(Object entity) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LogOperations.getInstance().createGeneralErrorLog("INSERTANDGET", entity.getClass().toString(),
					e.getCause().toString());
			return null;
		}
		return entity;
	}

	@Override
	public boolean update(Object entity) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LogOperations.getInstance().createGeneralErrorLog("UPDATE", entity.getClass().toString(),
					e.getCause().toString());
			return false;
		}
		return true;
	}

	@Override
	public Object updateAndGetEntity(Object entity) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LogOperations.getInstance().createGeneralErrorLog("UPDATEANDGET", entity.getClass().toString(),
					e.getCause().toString());
			return null;
		}
		return entity;
	}

	@Override
	public boolean delete(Object entity) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			session.delete(entity);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			LogOperations.getInstance().createGeneralErrorLog("DELETE", entity.getClass().toString(),
					e.getCause().toString());
			return false;
		}
		return true;
	}

}
