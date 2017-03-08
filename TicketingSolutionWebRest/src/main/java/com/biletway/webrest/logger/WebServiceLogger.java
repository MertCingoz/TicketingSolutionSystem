package com.biletway.webrest.logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.biletway.webrest.response.WebRestResponse;

public class WebServiceLogger {
	static Logger logger = Logger.getLogger(WebServiceLogger.class);

	public static void insertServiceLog(String serviceName, HttpServletRequest req, WebRestResponse response) {

	}
}
