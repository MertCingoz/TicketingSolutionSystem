package com.biletway.webrest.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextServiceUtil {
	private static final ApplicationContext appContext = buildAppContextService();

	public static ApplicationContext buildAppContextService() {
		try {
			return new ClassPathXmlApplicationContext("springService.xml");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static ApplicationContext getApplicationContext() {
		return appContext;
	}
}
