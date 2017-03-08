package com.biletway.service.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {
	private static final ApplicationContext appContext = buildAppContext();

	public static ApplicationContext buildAppContext() {
		try {
			return new ClassPathXmlApplicationContext("spring.xml");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static ApplicationContext getApplicationContext() {
		return appContext;
	}
}
