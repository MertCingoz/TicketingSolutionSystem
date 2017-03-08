package com.biletway.rest.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biletway.dao.util.HibernateUtil;
import com.biletway.rest.logger.ServiceLogger;
import com.biletway.rest.model.FileBucket;
import com.biletway.rest.resonse.RestResponse;
import com.biletway.rest.util.ApplicationContextServiceUtil;
import com.biletway.rest.util.CommonHelper;
import com.biletway.rest.util.FileValidator;
import com.biletway.service.interfaces.ApplicationSettingsService;
import com.biletway.service.interfaces.CityService;
import com.biletway.service.interfaces.EventService;
import com.biletway.service.interfaces.HallBlockService;
import com.biletway.service.interfaces.HallService;
import com.biletway.service.interfaces.MenuService;
import com.biletway.service.interfaces.PaymentMethodService;
import com.biletway.service.interfaces.PriceListDetailService;
import com.biletway.service.interfaces.PriceListService;
import com.biletway.service.interfaces.PriceTypeService;
import com.biletway.service.interfaces.SeatCategoryService;
import com.biletway.service.interfaces.SeatDesignService;
import com.biletway.service.interfaces.SeatDivService;
import com.biletway.service.interfaces.SeatingPlanDetailService;
import com.biletway.service.interfaces.SeatingPlanService;
import com.biletway.service.interfaces.ShowService;
import com.biletway.service.interfaces.ShowTypeService;
import com.biletway.service.interfaces.TicketDesignService;
import com.biletway.service.interfaces.TicketElementService;
import com.biletway.service.interfaces.TicketPoolService;
import com.biletway.service.interfaces.TicketSaleService;
import com.biletway.service.interfaces.TicketTypeService;
import com.biletway.service.interfaces.VenueService;
import com.biletway.service.response.ServiceResponse;

@RestController
public class TicketingSolutionRestController {
	Session session;
	HallService hallService = (HallService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("hallService");
	SeatCategoryService seatCategoryService = (SeatCategoryService) ApplicationContextServiceUtil
			.getApplicationContext().getBean("seatCategoryService");
	MenuService menuService = (MenuService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("menuService");
	PriceTypeService priceTypeService = (PriceTypeService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("priceTypeService");
	PriceListService priceListService = (PriceListService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("priceListService");
	TicketTypeService ticketTypeService = (TicketTypeService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("ticketTypeService");
	PriceListDetailService priceListDetailService = (PriceListDetailService) ApplicationContextServiceUtil
			.getApplicationContext().getBean("priceListDetailService");
	CityService cityService = (CityService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("cityService");
	VenueService venueService = (VenueService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("venueService");
	HallBlockService hallBlockService = (HallBlockService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("hallBlockService");
	SeatingPlanService seatingPlanService = (SeatingPlanService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("seatingPlanService");
	SeatingPlanDetailService seatingPlanDetailService = (SeatingPlanDetailService) ApplicationContextServiceUtil
			.getApplicationContext().getBean("seatingPlanDetailService");
	SeatDesignService seatDesignService = (SeatDesignService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("seatDesignService");
	SeatDivService seatDivService = (SeatDivService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("seatDivService");
	ShowTypeService showTypeService = (ShowTypeService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("showTypeService");
	ApplicationSettingsService applicationSettingsService = (ApplicationSettingsService) ApplicationContextServiceUtil
			.getApplicationContext().getBean("applicationSettingsService");
	ShowService showService = (ShowService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("showService");
	PaymentMethodService paymentMethodService = (PaymentMethodService) ApplicationContextServiceUtil
			.getApplicationContext().getBean("paymentMethodService");
	EventService eventService = (EventService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("eventService");
	TicketDesignService ticketDesignService = (TicketDesignService) ApplicationContextServiceUtil
			.getApplicationContext().getBean("ticketDesignService");
	TicketElementService ticketElementService = (TicketElementService) ApplicationContextServiceUtil
			.getApplicationContext().getBean("ticketElementService");
	TicketPoolService ticketPoolService = (TicketPoolService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("ticketPoolService");
	TicketSaleService ticketSaleService = (TicketSaleService) ApplicationContextServiceUtil.getApplicationContext()
			.getBean("ticketSaleService");

	public static String ROOT = "upload-dir";
	private static String UPLOAD_LOCATION;
	private static String UPLOAD_LOCATION_HREF;
	private static String BG_UPLOAD_LOCATION;
	private static String BG_UPLOAD_LOCATION_HREF;
	private static String TICKET_IMAGE_UPLOAD_LOCATION;
	private static String TICKET_IMAGE_UPLOAD_LOCATION_HREF;

	@RequestMapping(value = "/isServicesUp/", method = RequestMethod.GET)
	public boolean isServicesUp(HttpServletRequest req) {
		ServiceLogger.insertServiceLog("isServicesUp", req, new RestResponse());
		return true;
	}

	/////// SEAT CATEGORY START///////////////

	@RequestMapping(value = "/createSeatCategory/", method = RequestMethod.GET)
	public RestResponse createSeatCategory(@RequestParam("name") String name, @RequestParam("color") String color,
			HttpServletRequest req) {
		ServiceResponse insertServiceResponse = seatCategoryService.insert(name, color);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(insertServiceResponse);
		ServiceLogger.insertServiceLog("createSeatCategory", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/updateSeatCategory/", method = RequestMethod.GET)
	public RestResponse updateSeatCategory(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			@RequestParam("color") String color, HttpServletRequest req) {
		ServiceResponse updateServiceResponse = seatCategoryService.update(id, name, color);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(updateServiceResponse);
		ServiceLogger.insertServiceLog("updateSeatCategory", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/deleteSeatCategory/", method = RequestMethod.GET)
	public RestResponse deleteSeatCategory(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatCategoryService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteSeatCategory", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getSeatCategoryById/", method = RequestMethod.GET)
	public RestResponse getSeatCategoryById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(seatCategoryService.getSeatCategoryById(id));
		ServiceLogger.insertServiceLog("getSeatCategoryById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllSeatCategories/", method = RequestMethod.GET)
	public RestResponse getAllSeatCategoriesService(HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(seatCategoryService.getAllSeatCategories());
		ServiceLogger.insertServiceLog("getAllSeatCategories", req, response);
		return response;
	}

	///////// SEAT CATEGORY END///////////////

	///////// MENU OPERATION START///////////////

	@RequestMapping(value = "/getMenuTree/", method = RequestMethod.GET)
	public RestResponse getMenuTree(@RequestParam("userid") String userId, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(menuService.getMenuTree(userId));
		ServiceLogger.insertServiceLog("getMenuTree", req, response);
		return response;
	}

	/////////// MENU OPERATION END///////////////

	/////////// PRICE LIST START///////////////

	@RequestMapping(value = "/createPriceType/", method = RequestMethod.GET)
	public RestResponse createPriceType(@RequestParam("name") String name,
			@RequestParam("reportName") String reportName, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(priceTypeService.insert(name, reportName));
		ServiceLogger.insertServiceLog("createPriceType", req, response);
		return response;
	}

	@RequestMapping(value = "/updatePriceType/", method = RequestMethod.GET)
	public RestResponse updatePriceType(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			@RequestParam("reportName") String reportName, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(priceTypeService.update(id, name, reportName));
		ServiceLogger.insertServiceLog("updatePriceType", req, response);
		return response;
	}

	@RequestMapping(value = "/deletePriceType/", method = RequestMethod.GET)
	public RestResponse deletePriceType(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = priceTypeService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteSeatCategory", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getPriceTypeById/", method = RequestMethod.GET)
	public RestResponse getPriceTypeById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(priceTypeService.getPriceTypeById(id));
		ServiceLogger.insertServiceLog("getPriceTypeById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllPriceTypes/", method = RequestMethod.GET)
	public RestResponse getAllPriceTypes(HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(priceTypeService.getAllPriceTypes());
		ServiceLogger.insertServiceLog("getAllPriceTypes", req, response);
		return response;
	}

	/////////// PRICE TYPE END///////////////

	/////////// PRICE LIST START///////////////

	@RequestMapping(value = "/createPriceList/", method = RequestMethod.GET)
	public RestResponse createPriceList(@RequestParam("name") String name, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(priceListService.insert(name));
		ServiceLogger.insertServiceLog("createPriceList", req, response);
		return response;
	}

	@RequestMapping(value = "/updatePriceList/", method = RequestMethod.GET)
	public RestResponse updatePriceList(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(priceListService.update(id, name));
		ServiceLogger.insertServiceLog("updatePriceList", req, response);
		return response;
	}

	@RequestMapping(value = "/deletePriceList/", method = RequestMethod.GET)
	public RestResponse searchPriceList(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(priceListService.delete(id));
		ServiceLogger.insertServiceLog("deletePriceList", req, response);
		return response;
	}

	@RequestMapping(value = "/getPriceListById/", method = RequestMethod.GET)
	public RestResponse getPriceListById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(priceListService.getPriceListById(id));
		ServiceLogger.insertServiceLog("getPriceListById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllPriceLists/", method = RequestMethod.GET)
	public RestResponse getAllPriceLists(HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(priceListService.getAllPriceLists());
		ServiceLogger.insertServiceLog("getAllPriceLists", req, response);
		return response;
	}

	///////////// PRICE LIST END///////////////

	/////////// TICKET TYPE START///////////////

	@RequestMapping(value = "/getAllTicketTypes/", method = RequestMethod.GET)
	public RestResponse getAllTicketTypes(HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketTypeService.getAllTicketTypes());
		ServiceLogger.insertServiceLog("getAllTicketTypes", req, response);
		return response;
	} /////////// TICKET TYPE END///////////////

	/////////// PRICE LIST DETAIL START///////////////

	@RequestMapping(value = "/savePriceListDetail/", method = RequestMethod.GET)
	public RestResponse savePriceListDetail(@RequestParam("priceTypeId") BigInteger priceTypeId,
			@RequestParam("priceListId") BigInteger priceListId,
			@RequestParam("seatCategoryId") BigInteger seatCategoryId, @RequestParam("price") double price,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(
				priceListDetailService.insert(priceTypeId, priceListId, seatCategoryId, price));
		ServiceLogger.insertServiceLog("savePriceListDetail", req, response);
		return response;
	}

	@RequestMapping(value = "/getPriceListDetailList/", method = RequestMethod.GET)
	public RestResponse getPriceListDetailList(@RequestParam("priceListId") BigInteger priceListId,
			HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(priceListDetailService.getPriceListDetailList(priceListId));
		ServiceLogger.insertServiceLog("getPriceListDetailList", req, response);
		return response;
	}

	@RequestMapping(value = "/getPriceListDetailByShowId/", method = RequestMethod.GET)
	public RestResponse getPriceListDetailByShowId(@RequestParam("showId") BigInteger showId, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(priceListDetailService.getPriceListDetailByShowId(showId));
		ServiceLogger.insertServiceLog("getPriceListDetailList", req, response);
		return response;
	}
	/////////// PRICE LIST DETAIL END///////////////

	/////////// CITY START///////////////

	@RequestMapping(value = "/getAllCities/", method = RequestMethod.GET)
	public RestResponse getAllCities(HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(cityService.getAllCities());
		ServiceLogger.insertServiceLog("getAllCities", req, response);
		return response;
	} /////////// CITY END///////////////

	/////////// VENUE START///////////////

	@RequestMapping(value = "/getAllVenues/", method = RequestMethod.GET)
	public RestResponse getAllVenues(HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(venueService.getAllVenues());
		ServiceLogger.insertServiceLog("getAllVenues", req, response);
		return response;
	}

	@RequestMapping(value = "/createVenue/", method = RequestMethod.GET)
	public RestResponse createVenue(@RequestParam("name") String name, @RequestParam("countryId") BigInteger countryId,
			@RequestParam("stateId") BigInteger stateId, @RequestParam("cityId") BigInteger cityId,
			@RequestParam("address") String address, @RequestParam("postalCode") String postalCode,
			@RequestParam("phone") String phone, @RequestParam("fax") String fax, @RequestParam("email") String email,
			@RequestParam("venueUrl") String venueUrl, @RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(venueService.insert(name, countryId,
				stateId, cityId, address, postalCode, phone, fax, email, venueUrl, latitude, longitude));
		ServiceLogger.insertServiceLog("createVenue", req, response);
		return response;
	}

	@RequestMapping(value = "/getVenueById/", method = RequestMethod.GET)
	public RestResponse getVenueById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(venueService.getVenueById(id));
		ServiceLogger.insertServiceLog("getVenueById", req, response);
		return response;
	}

	@RequestMapping(value = "/updateVenue/", method = RequestMethod.GET)
	public RestResponse updateVenue(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			@RequestParam("countryId") BigInteger countryId, @RequestParam("stateId") BigInteger stateId,
			@RequestParam("cityId") BigInteger cityId, @RequestParam("address") String address,
			@RequestParam("postalCode") String postalCode, @RequestParam("phone") String phone,
			@RequestParam("fax") String fax, @RequestParam("email") String email,
			@RequestParam("venueUrl") String venueUrl, @RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(venueService.update(id, name,
				countryId, stateId, cityId, address, postalCode, phone, fax, email, venueUrl, latitude, longitude));
		ServiceLogger.insertServiceLog("createVenue", req, response);
		return response;
	}

	/////////// VENUE END///////////////

	/////////// HALL START///////////////
	@RequestMapping(value = "/getAllHallsByVenueId/", method = RequestMethod.GET)
	public RestResponse getAllHalls(@RequestParam("venueId") BigInteger venueId, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(hallService.getAllHallsByVenueId(venueId));
		ServiceLogger.insertServiceLog("getAllHallsByVenueId", req, response);
		return response;
	}

	@RequestMapping(value = "/createHall/", method = RequestMethod.GET)
	public RestResponse createHall(@RequestParam("name") String name, @RequestParam("venueId") BigInteger venueId,
			@RequestParam("allowOverlapEvents") String allowOverlapEvents, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(hallService.insert(name, venueId, allowOverlapEvents));
		ServiceLogger.insertServiceLog("createHall", req, response);
		return response;
	}

	@RequestMapping(value = "/getHallById/", method = RequestMethod.GET)
	public RestResponse getHallById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(hallService.getHallById(id));
		ServiceLogger.insertServiceLog("getHallById", req, response);
		return response;
	}

	@RequestMapping(value = "/updateHall/", method = RequestMethod.GET)
	public RestResponse updateHall(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			@RequestParam("allowOverlapEvents") String allowOverlapEvents, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(hallService.update(id, name, allowOverlapEvents));
		ServiceLogger.insertServiceLog("updateHall", req, response);
		return response;
	}

	/////////// HALL END///////////////

	/////////// HALL BLOCK START///////////////

	@RequestMapping(value = "/getAllHallBlocksByHallId/", method = RequestMethod.GET)
	public RestResponse getAllHallBlocksByHallId(@RequestParam("hallId") BigInteger hallId, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(hallBlockService.getAllHallBlocksByHallId(hallId));
		ServiceLogger.insertServiceLog("getAllHallBlocksByHallId", req, response);
		return response;
	}

	@RequestMapping(value = "/createHallBlock/", method = RequestMethod.GET)
	public RestResponse createHallBlockService(@RequestParam("name") String name,
			@RequestParam("venueId") BigInteger venueId, @RequestParam("hallId") BigInteger hallId,
			HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(hallBlockService.insert(name, venueId, hallId));
		ServiceLogger.insertServiceLog("createHallBlockService", req, response);
		return response;
	}

	@RequestMapping(value = "/getHallBlockById/", method = RequestMethod.GET)
	public RestResponse getHallBlockById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(hallBlockService.getHallBlockById(id));
		ServiceLogger.insertServiceLog("getHallById", req, response);
		return response;
	}

	@RequestMapping(value = "/updateHallBlock/", method = RequestMethod.GET)
	public RestResponse updateHallBlockService(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(hallBlockService.update(id, name));
		ServiceLogger.insertServiceLog("updateHallBlockService", req, response);
		return response;
	}

	/////////// HALL BLOCK END///////////////

	/////// SEATING PLAN START///////////////

	@RequestMapping(value = "/createSeatingPlan/", method = RequestMethod.GET)
	public RestResponse createSeatingPlan(@RequestParam("name") String name, @RequestParam("hallId") BigInteger hallId,
			@RequestParam("background") String background, HttpServletRequest req) {
		ServiceResponse insertServiceResponse = seatingPlanService.insert(name, hallId, background);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(insertServiceResponse);
		ServiceLogger.insertServiceLog("createSeatingPlan", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/updateSeatingPlan/", method = RequestMethod.GET)
	public RestResponse updateSeatingPlan(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			@RequestParam("background") String background, HttpServletRequest req) {
		ServiceResponse updateServiceResponse = seatingPlanService.update(id, name, background);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(updateServiceResponse);
		ServiceLogger.insertServiceLog("updateSeatingPlan", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/updateSeatingPlanImage/", method = RequestMethod.GET)
	public RestResponse updateSeatingPlanImage(@RequestParam("id") BigInteger id,
			@RequestParam("background") String background, HttpServletRequest req) {
		ServiceResponse updateServiceResponse = seatingPlanService.updateSeatingPlanImage(id, background);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(updateServiceResponse);
		ServiceLogger.insertServiceLog("updateSeatingPlanImage", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/deleteSeatingPlan/", method = RequestMethod.GET)
	public RestResponse deleteSeatingPlan(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatingPlanService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteSeatingPlan", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getSeatingPlanById/", method = RequestMethod.GET)
	public RestResponse getSeatingPlanById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(seatingPlanService.getSeatingPlanById(id));
		ServiceLogger.insertServiceLog("getSeatingPlanById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllSeatingPlansByHallId/", method = RequestMethod.GET)
	public RestResponse getAllSeatingPlansByHallId(@RequestParam("hallId") BigInteger hallId, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(seatingPlanService.getAllSeatingPlansByHallId(hallId));
		ServiceLogger.insertServiceLog("getAllSeatingPlansByHallId", req, response);
		return response;
	}

	///////// SEATING PLAN END///////////////

	/////// SEATING PLAN DETAIL START///////////////

	@RequestMapping(value = "/createSeatingPlanDetail/", method = RequestMethod.GET)
	public RestResponse createSeatingPlanDetail(@RequestParam("seatingPlanDetailId") BigInteger seatingPlanDetailId,
			@RequestParam("seatingPlanId") BigInteger seatingPlanId,
			@RequestParam("hallBlockId") BigInteger hallBlockId,
			@RequestParam("hallBlockCapacity") int hallBlockCapacity,
			@RequestParam("hasSeatingPlan") String hasSeatingPlan, @RequestParam("drawingArea") String drawingArea,
			HttpServletRequest req) {
		ServiceResponse insertServiceResponse = seatingPlanDetailService.insert(seatingPlanDetailId, seatingPlanId,
				hallBlockId, hallBlockCapacity, hasSeatingPlan, drawingArea);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(insertServiceResponse);
		ServiceLogger.insertServiceLog("createSeatingPlanDetail", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/deleteSeatingPlanDetail/", method = RequestMethod.GET)
	public RestResponse deleteSeatingPlanDetail(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatingPlanDetailService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteSeatingPlanDetail", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getSeatingPlanDetailById/", method = RequestMethod.GET)
	public RestResponse getSeatingPlanDetailById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(seatingPlanDetailService.getSeatingPlanDetailById(id));
		ServiceLogger.insertServiceLog("getSeatingPlanDetailById", req, response);
		return response;
	}

	@RequestMapping(value = "/getSeatingPlanDetailBySeatingPlanId/", method = RequestMethod.GET)
	public RestResponse getSeatingPlanDetailBySeatingPlanId(@RequestParam("seatingPlanId") BigInteger seatingPlanId,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(
				seatingPlanDetailService.getSeatingPlanDetailBySeatingPlanId(seatingPlanId));
		ServiceLogger.insertServiceLog("getSeatingPlanDetailBySeatingPlanId", req, response);
		return response;
	}

	@RequestMapping(value = "/changeSeatingPlanDetailStatusToPassive/", method = RequestMethod.GET)
	public RestResponse changeSeatingPlanDetailStatusToPassive(@RequestParam("seatingPlanId") BigInteger seatingPlanId,
			@RequestParam("activeSeatingPlanDetailIds") String activeSeatingPlanDetailIds, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatingPlanDetailService
				.changeSeatingPlanDetailStatusToPassive(seatingPlanId, activeSeatingPlanDetailIds);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("changeSeatingPlanDetailStatusToPassive", req, restResponse);
		return restResponse;
	}

	///////// SEATING PLAN DETAIL END///////////////

	/////// SEAT DESIGN START///////////////

	@RequestMapping(value = "/createSeatDesign/", method = RequestMethod.GET)
	public RestResponse createSeatDesign(@RequestParam("seatingPlanDetailId") BigInteger seatingPlanDetailId,
			@RequestParam("rowName") String rowName, @RequestParam("seatNumber") int seatNumber,
			@RequestParam("rowPattern") String rowPattern, @RequestParam("rowOrder") int rowOrder,
			@RequestParam("seatDivs") String seatDivs, HttpServletRequest req) {
		ServiceResponse insertServiceResponse = seatDesignService.insert(seatingPlanDetailId, rowName, seatNumber,
				rowPattern, rowOrder, seatDivs);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(insertServiceResponse);
		ServiceLogger.insertServiceLog("createSeatDesign", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/deleteSeatDesign/", method = RequestMethod.GET)
	public RestResponse deleteSeatDesign(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatDesignService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteSeatDesign", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getSeatDesignById/", method = RequestMethod.GET)
	public RestResponse getSeatDesignById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(seatDesignService.getSeatDesignById(id));
		ServiceLogger.insertServiceLog("getSeatDesignById", req, response);
		return response;
	}

	@RequestMapping(value = "/getSeatDesignBySeatingPlanDetailId/", method = RequestMethod.GET)
	public RestResponse getSeatDesignBySeatingPlanDetailId(
			@RequestParam("seatingPlanDetailId") BigInteger seatingPlanDetailId, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(
				seatDesignService.getSeatDesignBySeatingPlanDetailId(seatingPlanDetailId));
		ServiceLogger.insertServiceLog("getSeatDesignBySeatingPlanDetailId", req, response);
		return response;
	}

	@RequestMapping(value = "/deleteAllSeatDesignBySeatingPlanDetailId/", method = RequestMethod.GET)
	public RestResponse deleteAllSeatDesignBySeatingPlanDetailId(
			@RequestParam("seatingPlanDetailId") BigInteger seatingPlanDetailId, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatDesignService
				.deleteAllSeatDesignBySeatingPlanDetailId(seatingPlanDetailId);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteAllSeatDesignBySeatingPlanDetailId", req, restResponse);
		return restResponse;
	}

	///////// SEATING DESIGN END///////////////

	/////// SEAT DIV START///////////////

	@RequestMapping(value = "/createSeatDiv/", method = RequestMethod.GET)
	public RestResponse createSeatDiv(@RequestParam("seatDesignId") BigInteger seatDesignId,
			@RequestParam("divId") String divId, @RequestParam("seatType") String seatType,
			@RequestParam("seatCategory") String seatCategory, HttpServletRequest req) {
		ServiceResponse insertServiceResponse = seatDivService.insert(seatDesignId, divId, seatType, seatCategory);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(insertServiceResponse);
		ServiceLogger.insertServiceLog("createSeatDiv", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/deleteSeatDiv/", method = RequestMethod.GET)
	public RestResponse deleteSeatDiv(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatDivService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteSeatDiv", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getSeatDivById/", method = RequestMethod.GET)
	public RestResponse getSeatDivById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(seatDivService.getSeatDivById(id));
		ServiceLogger.insertServiceLog("getSeatDivById", req, response);
		return response;
	}

	@RequestMapping(value = "/getSeatDivBySeatDesignId/", method = RequestMethod.GET)
	public RestResponse getSeatDivBySeatDesignId(@RequestParam("seatDesignId") BigInteger seatDesignId,
			HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(seatDivService.getSeatDivBySeatDesignId(seatDesignId));
		ServiceLogger.insertServiceLog("getSeatDivBySeatDesignId", req, response);
		return response;
	}

	@RequestMapping(value = "/deleteAllSeatDivBySeatDesignId/", method = RequestMethod.GET)
	public RestResponse deleteAllSeatDivBySeatDesignId(@RequestParam("seatDesignId") BigInteger seatDesignId,
			HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = seatDivService.deleteAllSeatDivBySeatDesignId(seatDesignId);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteAllSeatDivBySeatDesignId", req, restResponse);
		return restResponse;
	}

	///////// SEATING DIV END///////////////

	/////////// SHOW TYPE START///////////////

	@RequestMapping(value = "/createShowType/", method = RequestMethod.GET)
	public RestResponse createShowType(@RequestParam("name") String name, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showTypeService.insert(name));
		ServiceLogger.insertServiceLog("createShowType", req, response);
		return response;
	}

	@RequestMapping(value = "/updateShowType/", method = RequestMethod.GET)
	public RestResponse updateShowType(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showTypeService.update(id, name));
		ServiceLogger.insertServiceLog("updateShowType", req, response);
		return response;
	}

	@RequestMapping(value = "/deleteShowType/", method = RequestMethod.GET)
	public RestResponse deleteShowType(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = showTypeService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteShowType", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getShowTypeById/", method = RequestMethod.GET)
	public RestResponse getShowTypeById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showTypeService.getShowTypeById(id));
		ServiceLogger.insertServiceLog("getShowTypeById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllShowTypes/", method = RequestMethod.GET)
	public RestResponse getAllShowTypes(HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showTypeService.getAllShowTypes());
		ServiceLogger.insertServiceLog("getAllShowTypes", req, response);
		return response;
	}

	/////////// SHOW TYPE END///////////////

	/////////// SHOW START///////////////

	@RequestMapping(value = "/createShow/", method = RequestMethod.GET)
	public RestResponse createShow(@RequestParam("showTypeId") BigInteger showTypeId, @RequestParam("name") String name,
			@RequestParam("summary") String summary, @RequestParam("description") String description,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("duration") int duration, @RequestParam("venueId") BigInteger venueId,
			@RequestParam("hallId") BigInteger hallId, @RequestParam("priceListId") BigInteger priceListId,
			@RequestParam("maxTicketSaleCount") int maxTicketSaleCount,
			@RequestParam("saleStartDate") String saleStartDate, @RequestParam("saleEndDate") String saleEndDate,
			@RequestParam("paymentMethods") String paymentMethods,
			@RequestParam("seatingPlanId") BigInteger seatingPlanId, @RequestParam("ticketCount") int ticketCount,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showService.insert(showTypeId, name,
				summary, description, CommonHelper.dateConvertor(startDate), CommonHelper.dateConvertor(endDate),
				duration, venueId, hallId, priceListId, maxTicketSaleCount, CommonHelper.dateConvertor(saleStartDate),
				CommonHelper.dateConvertor(saleEndDate), paymentMethods, seatingPlanId, ticketCount));
		ServiceLogger.insertServiceLog("createShow", req, response);
		return response;
	}

	@RequestMapping(value = "/updateShow/", method = RequestMethod.GET)
	public RestResponse updateShow(@RequestParam("showId") BigInteger showId,
			@RequestParam("showTypeId") BigInteger showTypeId, @RequestParam("name") String name,
			@RequestParam("summary") String summary, @RequestParam("description") String description,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("duration") int duration, @RequestParam("venueId") BigInteger venueId,
			@RequestParam("hallId") BigInteger hallId, @RequestParam("priceListId") BigInteger priceListId,
			@RequestParam("maxTicketSaleCount") int maxTicketSaleCount,
			@RequestParam("saleStartDate") String saleStartDate, @RequestParam("saleEndDate") String saleEndDate,
			@RequestParam("paymentMethods") String paymentMethods,
			@RequestParam("seatingPlanId") BigInteger seatingPlanId, @RequestParam("ticketCount") int ticketCount,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showService.update(showId, showTypeId,
				name, summary, description, CommonHelper.dateConvertor(startDate), CommonHelper.dateConvertor(endDate),
				duration, venueId, hallId, priceListId, maxTicketSaleCount, CommonHelper.dateConvertor(saleStartDate),
				CommonHelper.dateConvertor(saleEndDate), paymentMethods, seatingPlanId, ticketCount));
		ServiceLogger.insertServiceLog("updateShow", req, response);
		return response;
	}

	@RequestMapping(value = "/getShowById/", method = RequestMethod.GET)
	public RestResponse getShowById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showService.getShowById(id));
		ServiceLogger.insertServiceLog("getShowById", req, response);
		return response;
	}

	@RequestMapping(value = "/getShowByIdToCreateEvent/", method = RequestMethod.GET)
	public RestResponse getShowByIdToCreateEvent(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(showService.getShowByIdToCreateEvent(id));
		ServiceLogger.insertServiceLog("getShowByIdToCreateEvent", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllShows/", method = RequestMethod.GET)
	public RestResponse getAllShows(HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(showService.getAllShows());
		ServiceLogger.insertServiceLog("getAllShows", req, response);
		return response;
	}

	/////////// SHOW END///////////////

	/////////// PAYMENT METHOD START///////////////

	@RequestMapping(value = "/createPaymentMethod/", method = RequestMethod.GET)
	public RestResponse createPaymentMethod(@RequestParam("name") String name, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(paymentMethodService.insert(name));
		ServiceLogger.insertServiceLog("createPaymentMethod", req, response);
		return response;
	}

	@RequestMapping(value = "/updatePaymentMethod/", method = RequestMethod.GET)
	public RestResponse updatePaymentMethod(@RequestParam("id") BigInteger id, @RequestParam("name") String name,
			HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(paymentMethodService.update(id, name));
		ServiceLogger.insertServiceLog("updatePaymentMethod", req, response);
		return response;
	}

	@RequestMapping(value = "/deletePaymentMethod/", method = RequestMethod.GET)
	public RestResponse deletePaymentMethod(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = paymentMethodService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deletePaymentMethod", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getPaymentMethodById/", method = RequestMethod.GET)
	public RestResponse getPaymentMethodById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(paymentMethodService.getPaymentMethodById(id));
		ServiceLogger.insertServiceLog("getPaymentMethodById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllPaymentMethods/", method = RequestMethod.GET)
	public RestResponse getAllPaymentMethods(HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(paymentMethodService.getAllPaymentMethods());
		ServiceLogger.insertServiceLog("getAllPaymentMethods", req, response);
		return response;
	}

	/////////// PRICE TYPE END///////////////

	/////////// EVENT START///////////////
	@RequestMapping(value = "/getAllEventsByShowId/", method = RequestMethod.GET)
	public RestResponse getAllEventsByShowId(@RequestParam("showId") BigInteger showId, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(eventService.getAllEventsByShowId(showId));
		ServiceLogger.insertServiceLog("getAllEventsByShowId", req, response);
		return response;
	}

	@RequestMapping(value = "/createEvent/", method = RequestMethod.GET)
	public RestResponse createHall(@RequestParam("showId") BigInteger showId,
			@RequestParam("eventDate") String eventDate, @RequestParam("duration") int duration,
			@RequestParam("selectSeat") int selectSeat, @RequestParam("saleStatus") int saleStatus,
			@RequestParam("ticketDesignId") BigInteger ticketDesignId,
			@RequestParam("createUser") BigInteger createUser, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(eventService.insert(showId, CommonHelper.dateConvertor(eventDate),
						duration, selectSeat, saleStatus, ticketDesignId, new Date(), createUser));
		ServiceLogger.insertServiceLog("createHall", req, response);
		return response;
	}

	@RequestMapping(value = "/getEventById/", method = RequestMethod.GET)
	public RestResponse getEventById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(eventService.getEventById(id));
		ServiceLogger.insertServiceLog("getEventById", req, response);
		return response;
	}

	@RequestMapping(value = "/updateEvent/", method = RequestMethod.GET)
	public RestResponse updateEvent(@RequestParam("id") BigInteger id, @RequestParam("eventDate") String eventDate,
			@RequestParam("duration") int duration, @RequestParam("selectSeat") int selectSeat,
			@RequestParam("saleStatus") int saleStatus, @RequestParam("ticketDesignId") BigInteger ticketDesignId,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(eventService.update(id,
				CommonHelper.dateConvertor(eventDate), duration, selectSeat, saleStatus, ticketDesignId));
		ServiceLogger.insertServiceLog("updateEvent", req, response);
		return response;
	}

	/////////// EVENT END///////////////

	/////////// TICKET DESIGN START///////////////

	@RequestMapping(value = "/createTicketDesign/", method = RequestMethod.GET)
	public RestResponse createTicketDesign(@RequestParam("backgroundImage") String backgroundImage,
			@RequestParam("name") String name, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketDesignService.insert(backgroundImage, name));
		ServiceLogger.insertServiceLog("createTicketDesign", req, response);
		return response;
	}

	@RequestMapping(value = "/updateTicketDesign/", method = RequestMethod.GET)
	public RestResponse updateTicketDesign(@RequestParam("id") BigInteger id,
			@RequestParam("backgroundImage") String backgroundImage, @RequestParam("name") String name,
			HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketDesignService.update(id, name, backgroundImage));
		ServiceLogger.insertServiceLog("updateTicketDesign", req, response);
		return response;
	}

	@RequestMapping(value = "/deleteTicketDesign/", method = RequestMethod.GET)
	public RestResponse deleteTicketDesign(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = ticketDesignService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteTicketDesign", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getTicketDesignById/", method = RequestMethod.GET)
	public RestResponse getTicketDesignById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketDesignService.getTicketDesignById(id));
		ServiceLogger.insertServiceLog("getTicketDesignById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllTicketDesigns/", method = RequestMethod.GET)
	public RestResponse getAllTicketDesigns(HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketDesignService.getAllTicketDesigns());
		ServiceLogger.insertServiceLog("getAllTicketDesigns", req, response);
		return response;
	}
	/////////// TICKET DESIGN END///////////////

	/////////// TICKET ELEMENT START///////////////

	@RequestMapping(value = "/createTicketElement/", method = RequestMethod.GET)
	public RestResponse createTicketElement(@RequestParam("ticketDesignId") BigInteger ticketDesignId,
			@RequestParam("elementType") String elementType, @RequestParam("elementId") String elementId,
			@RequestParam("xAxis") int xAxis, @RequestParam("yAxis") int yAxis, @RequestParam("order") int order,
			@RequestParam("fieldType") String fieldType, @RequestParam("width") int width,
			@RequestParam("height") int height, @RequestParam("picture") String picture,
			@RequestParam("font") String font, @RequestParam("size") String size,
			@RequestParam("dbFileName") String dbFileName, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketElementService.insert(ticketDesignId, elementType,
						elementId, xAxis, yAxis, order, fieldType, width, height, picture, font, size, dbFileName));
		ServiceLogger.insertServiceLog("createTicketElement", req, response);
		return response;
	}

	@RequestMapping(value = "/updateTicketElement/", method = RequestMethod.GET)
	public RestResponse updateTicketElement(@RequestParam("id") BigInteger id,
			@RequestParam("elementType") String elementType, @RequestParam("elementId") String elementId,
			@RequestParam("xAxis") int xAxis, @RequestParam("yAxis") int yAxis, @RequestParam("order") int order,
			@RequestParam("fieldType") String fieldType, @RequestParam("width") int width,
			@RequestParam("height") int height, @RequestParam("picture") String picture,
			@RequestParam("font") String font, @RequestParam("size") String size,
			@RequestParam("dbFileName") String dbFileName, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketElementService.update(id, elementType, elementId, xAxis,
						yAxis, order, fieldType, width, height, picture, font, size, dbFileName));
		ServiceLogger.insertServiceLog("updateTicketElement", req, response);
		return response;
	}

	@RequestMapping(value = "/deleteTicketElement/", method = RequestMethod.GET)
	public RestResponse deleteTicketElement(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		ServiceResponse deleteServiceResponse = ticketElementService.delete(id);
		RestResponse restResponse = CommonHelper.getResponseObjectFromServiceResponse(deleteServiceResponse);
		ServiceLogger.insertServiceLog("deleteTicketElement", req, restResponse);
		return restResponse;
	}

	@RequestMapping(value = "/getTicketElementById/", method = RequestMethod.GET)
	public RestResponse getTicketElementById(@RequestParam("id") BigInteger id, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketElementService.getTicketElementById(id));
		ServiceLogger.insertServiceLog("getTicketElementById", req, response);
		return response;
	}

	@RequestMapping(value = "/getAllTicketElementsByTicketDesignId/", method = RequestMethod.GET)
	public RestResponse getAllTicketElementsByTicketDesignId(@RequestParam("ticketDesignId") BigInteger ticketDesignId,
			HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(
				ticketElementService.getAllTicketElementsByTicketDesignId(ticketDesignId));
		ServiceLogger.insertServiceLog("getTicketElementById", req, response);
		return response;
	}

	/////////// TICKET ELEMENT END///////////////

	/////////// TICKET POOL START///////////////

	@RequestMapping(value = "/generateBarcodeByEventId/", method = RequestMethod.GET)
	public RestResponse generateBarcodeByEventId(@RequestParam("eventId") BigInteger eventId,
			@RequestParam("showId") BigInteger showId, HttpServletRequest req) {
		RestResponse response = CommonHelper
				.getResponseObjectFromServiceResponse(ticketPoolService.generateTicketBarcodeForEvent(eventId, showId));
		ServiceLogger.insertServiceLog("getTicketElementById", req, response);
		return response;
	}

	/////////// TICKET POOL END///////////////

	/////////// TICKET SALE OPERATIONS START///////////////

	@RequestMapping(value = "/saleTicketByBackOffice/", method = RequestMethod.GET)
	public RestResponse saleTicketByBackOffice(@RequestParam("showId") BigInteger showId,
			@RequestParam("eventId") BigInteger eventId, @RequestParam("priceTypeIdList") String priceTypeIdList,
			@RequestParam("ticketCountList") String ticketCountList, HttpServletRequest req) {
		RestResponse response = CommonHelper.getResponseObjectFromServiceResponse(
				ticketSaleService.saleTicketByBackOffice(showId, eventId, priceTypeIdList, ticketCountList));
		ServiceLogger.insertServiceLog("getTicketElementById", req, response);
		return response;
	}

	/////////// TICKET SALE OPERATIONS END///////////////

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucket")
	protected void initBinderFileBucket(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		return "welcome";
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
	public String getSingleUploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "singleFileUploader";
	}

	@RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
	public String singleFileUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model)
			throws IOException {
		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "0";
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileBucket.getFile();
			// Now do something with file...
			// fileBucket.getFile().getOriginalFilename()
			String newFileName = CommonHelper.getUUID().concat(".")
					.concat(FilenameUtils.getExtension(fileBucket.getFile().getOriginalFilename()));
			FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + newFileName));
			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			return UPLOAD_LOCATION_HREF.concat(newFileName);
		}
	}

	@RequestMapping(value = "/bgUpload", method = RequestMethod.GET)
	public String getbgUploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "singleFileUploader";
	}

	@RequestMapping(value = "/bgUpload", method = RequestMethod.POST)
	public String bgUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model) throws IOException {
		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "0";
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileBucket.getFile();
			// Now do something with file...
			// fileBucket.getFile().getOriginalFilename()
			String newFileName = CommonHelper.getUUID().concat(".")
					.concat(FilenameUtils.getExtension(fileBucket.getFile().getOriginalFilename()));
			FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(BG_UPLOAD_LOCATION + newFileName));
			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			return BG_UPLOAD_LOCATION_HREF.concat(newFileName);
		}
	}

	@RequestMapping(value = "/ticketImageUpload", method = RequestMethod.GET)
	public String getTicketImageUploadPage(ModelMap model) {
		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);
		return "singleFileUploader";
	}

	@RequestMapping(value = "/ticketImageUpload", method = RequestMethod.POST)
	public String ticketImageUpload(@Valid FileBucket fileBucket, BindingResult result, ModelMap model)
			throws IOException {
		if (result.hasErrors()) {
			System.out.println("validation errors");
			return "0";
		} else {
			System.out.println("Fetching file");
			MultipartFile multipartFile = fileBucket.getFile();
			// Now do something with file...
			// fileBucket.getFile().getOriginalFilename()
			String newFileName = CommonHelper.getUUID().concat(".")
					.concat(FilenameUtils.getExtension(fileBucket.getFile().getOriginalFilename()));
			FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(TICKET_IMAGE_UPLOAD_LOCATION + newFileName));
			String fileName = multipartFile.getOriginalFilename();
			model.addAttribute("fileName", fileName);
			return TICKET_IMAGE_UPLOAD_LOCATION_HREF.concat(newFileName);
		}
	}

	public TicketingSolutionRestController() {
		session = HibernateUtil.getSessionFactory().openSession();
		UPLOAD_LOCATION = applicationSettingsService.getValueByKey("UPLOAD_LOCATION");
		UPLOAD_LOCATION_HREF = applicationSettingsService.getValueByKey("UPLOAD_LOCATION_HREF");
		BG_UPLOAD_LOCATION = applicationSettingsService.getValueByKey("BG_UPLOAD_LOCATION");
		BG_UPLOAD_LOCATION_HREF = applicationSettingsService.getValueByKey("BG_UPLOAD_LOCATION_HREF");
		TICKET_IMAGE_UPLOAD_LOCATION = applicationSettingsService.getValueByKey("TICKET_IMAGE_UPLOAD_LOCATION");
		TICKET_IMAGE_UPLOAD_LOCATION_HREF = applicationSettingsService
				.getValueByKey("TICKET_IMAGE_UPLOAD_LOCATION_HREF");
	}

	@PreDestroy
	private void closeConnection() {
		HibernateUtil.shutdown();
	}

}
