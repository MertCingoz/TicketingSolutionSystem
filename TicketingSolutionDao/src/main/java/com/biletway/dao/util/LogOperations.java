package com.biletway.dao.util;

import java.util.Date;

import org.hibernate.Session;

import com.biletway.dao.entity.GeneralErrorLog;

public class LogOperations {

	private static LogOperations instance = null;

	public LogOperations() {
	}

	public static LogOperations getInstance() {
		if (instance == null) {
			instance = new LogOperations();
		}
		return instance;
	}

	public void createGeneralErrorLog(String operationType, String errorClass, String errorDescription) {
		Session session = null;
		try {
			GeneralErrorLog genErrLog = new GeneralErrorLog();
			genErrLog.setOperationType(operationType);
			genErrLog.setErrorClass(errorClass);
			genErrLog.setErrorDescription(errorDescription);
			genErrLog.setErrorTime(new Date());
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			session.save(genErrLog);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Sistem hatası loglanırken hata meydana geldi!");
		}
	}
}
