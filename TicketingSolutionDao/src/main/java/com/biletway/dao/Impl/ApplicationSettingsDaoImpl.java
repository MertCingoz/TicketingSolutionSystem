package com.biletway.dao.Impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.biletway.dao.entity.ApplicationSettings;
import com.biletway.dao.interfaces.ApplicationSettingsDao;
import com.biletway.dao.util.HibernateUtil;

public class ApplicationSettingsDaoImpl implements ApplicationSettingsDao {

	@Override
	public String getValueByKey(String key) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		ApplicationSettings applicationSetting = (ApplicationSettings) session.createCriteria(ApplicationSettings.class)
				.add(Restrictions.eq("key", key)).uniqueResult();
		session.getTransaction().commit();
		return applicationSetting.getValue();
	}

}
