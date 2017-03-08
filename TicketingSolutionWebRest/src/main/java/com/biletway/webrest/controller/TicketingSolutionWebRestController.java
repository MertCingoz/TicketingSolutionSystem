package com.biletway.webrest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biletway.service.interfaces.EventService;
import com.biletway.webrest.logger.WebServiceLogger;
import com.biletway.webrest.response.WebRestResponse;
import com.biletway.webrest.util.ApplicationContextServiceUtil;

@RestController
public class TicketingSolutionWebRestController {

	EventService eventService = (EventService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("eventService");

	@RequestMapping(value = "/isServicesUp/", method = RequestMethod.GET)
	public boolean isServicesUp(HttpServletRequest req) {
		WebServiceLogger.insertServiceLog("isServicesUp", req, new WebRestResponse());
		return true;
	}

	@RequestMapping(value = "/getHomePageEvents/", method = RequestMethod.GET)
	public WebRestResponse getHomePageEvents(HttpServletRequest req) {
		return new WebRestResponse();
	}
}
