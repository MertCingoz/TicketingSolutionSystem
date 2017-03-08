package com.biletway.rest.logger;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.biletway.dao.entity.ServiceLog;
import com.biletway.dao.util.HibernateUtil;
import com.biletway.rest.resonse.RestResponse;
import com.biletway.rest.util.RestConstants;

public class ServiceLogger {
	static Logger logger = Logger.getLogger(ServiceLogger.class);

	public static void insertServiceLog(String serviceName, HttpServletRequest req, RestResponse response) {
		Session session = null;
		try {
			StringBuffer requestURL = req.getRequestURL();
			String queryString = req.getQueryString();
			String url = requestURL.toString();
			if (queryString == null) {
				url = requestURL.toString();
			} else {
				logger.info("url: " + requestURL.append('?').append(queryString).toString());
			}

			String requestMethod = req.getMethod();

			String header = "";
			@SuppressWarnings("rawtypes")
			Enumeration headerNames = req.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				header += headerName + ":" + req.getHeader(headerName) + "|";
			}

			@SuppressWarnings("rawtypes")
			Enumeration params = req.getParameterNames();
			String input = "";
			while (params.hasMoreElements()) {
				String paramName = (String) params.nextElement();
				input += paramName + "=" + req.getParameter(paramName) + "|";
			}

			ServiceLog serviceLog = new ServiceLog();
			serviceLog.setStatus(RestConstants.DAO_ACTIVE_STATUS);
			serviceLog.setCreateDate(new Date(System.currentTimeMillis()));
			serviceLog.setHeader(header);
			serviceLog.setInput(input);
			serviceLog.setRequestMethod(requestMethod);
			serviceLog.setOutput(response.toString());
			serviceLog.setServiceName(serviceName);
			serviceLog.setUrl(url);
			serviceLog.setDuration(response.getServiceDuration());
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			session.save(serviceLog);
			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("ServiceLogger.insertServiceLog servisinde hata meydana geldi");
			logger.error(e);
			// session.getTransaction().rollback();
		}
	}

}
