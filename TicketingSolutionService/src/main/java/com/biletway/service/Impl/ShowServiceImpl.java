package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.biletway.dao.entity.Show;
import com.biletway.dao.entity.ShowPaymentMethod;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.HallDao;
import com.biletway.dao.interfaces.ShowDao;
import com.biletway.dao.interfaces.ShowPaymentMethodDao;
import com.biletway.dao.interfaces.ShowTypeDao;
import com.biletway.dao.interfaces.VenueDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.ShowService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.ShowGetAllListResponse;
import com.biletway.service.response.ShowGetByIdResponse;
import com.biletway.service.response.ShowGetByIdToCreateEventResponse;
import com.biletway.service.response.ShowInsertServiceResponse;
import com.biletway.service.response.ShowUpdateServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class ShowServiceImpl implements ShowService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	ShowDao showDao = (ShowDao) ApplicationContextUtil.getApplicationContext().getBean("showDao");
	ShowPaymentMethodDao showPaymentMethodDao = (ShowPaymentMethodDao) ApplicationContextUtil.getApplicationContext()
			.getBean("showPaymentMethodDao");
	HallDao hallDao = (HallDao) ApplicationContextUtil.getApplicationContext().getBean("hallDao");
	ShowTypeDao showTypeDao = (ShowTypeDao) ApplicationContextUtil.getApplicationContext().getBean("showTypeDao");
	VenueDao venueDao = (VenueDao) ApplicationContextUtil.getApplicationContext().getBean("venueDao");

	@Override
	public ServiceResponse insert(BigInteger showTypeId, String name, String summary, String description,
			Date startDate, Date endDate, int duration, BigInteger venueId, BigInteger hallId, BigInteger priceListId,
			int maxTicketSaleCount, Date saleStartDate, Date saleEndDate, String paymentMethods,
			BigInteger seatingPlanId, int ticketCount) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Show show = new Show();
		show.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		show.setDescription(description);
		show.setDuration(duration);
		show.setEndDate(endDate);
		show.setHallId(hallId);
		show.setMaxTicketSaleCount(maxTicketSaleCount);
		show.setName(name);
		show.setPriceListId(priceListId);
		show.setSaleEndDate(saleEndDate);
		show.setSaleStartDate(saleStartDate);
		show.setShowTypeId(showTypeId);
		show.setStartDate(startDate);
		show.setShowTypeId(showTypeId);
		show.setSummary(summary);
		show.setVenueId(venueId);
		show.setSeatingPlanId(seatingPlanId);
		show.setTicketCount(ticketCount);
		boolean insertResponse = commonOperationsDao.insert(show);
		ShowInsertServiceResponse serviceResponse = new ShowInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_SHOW_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_SHOW_ERROR_DESC;
		} else {
			String[] paymentMethodList = paymentMethods.split(",");
			for (int i = 0; i < paymentMethodList.length; i++) {
				ShowPaymentMethod showPaymentMethod = new ShowPaymentMethod();
				showPaymentMethod.setPaymentMethodId(new BigInteger(paymentMethodList[i]));
				showPaymentMethod.setShowId(show.getId());
				showPaymentMethod.setStatus(DaoConstants.ACTIVE_STATUS);
				commonOperationsDao.insert(showPaymentMethod);
			}
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger showId, BigInteger showTypeId, String name, String summary,
			String description, Date startDate, Date endDate, int duration, BigInteger venueId, BigInteger hallId,
			BigInteger priceListId, int maxTicketSaleCount, Date saleStartDate, Date saleEndDate, String paymentMethods,
			BigInteger seatingPlanId, int ticketCount) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Show show = new Show();
		show.setId(showId);
		show.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		show.setDescription(description);
		show.setDuration(duration);
		show.setEndDate(endDate);
		show.setHallId(hallId);
		show.setMaxTicketSaleCount(maxTicketSaleCount);
		show.setName(name);
		show.setPriceListId(priceListId);
		show.setSaleEndDate(saleEndDate);
		show.setSaleStartDate(saleStartDate);
		show.setShowTypeId(showTypeId);
		show.setStartDate(startDate);
		show.setShowTypeId(showTypeId);
		show.setSummary(summary);
		show.setVenueId(venueId);
		show.setSeatingPlanId(seatingPlanId);
		boolean insertResponse = commonOperationsDao.update(show);
		ShowUpdateServiceResponse serviceResponse = new ShowUpdateServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.UPDATE_SHOW_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_SHOW_ERROR_DESC;
		} else {
			String[] paymentMethodList = paymentMethods.split(",");
			List<ShowPaymentMethod> showPaymentMethodList = showPaymentMethodDao.getPaymentMethodsByShowId(showId);
			for (int i = 0; i < paymentMethodList.length; i++) {
				boolean found = false;
				for (ShowPaymentMethod showPaymentMethod : showPaymentMethodList) {
					if (showPaymentMethod.getPaymentMethodId().compareTo(new BigInteger(paymentMethodList[i])) == 0) {
						found = true;
					}
				}
				if (!found) {
					ShowPaymentMethod showPaymentMethod = new ShowPaymentMethod();
					showPaymentMethod.setPaymentMethodId(new BigInteger(paymentMethodList[i]));
					showPaymentMethod.setShowId(show.getId());
					showPaymentMethod.setStatus(DaoConstants.ACTIVE_STATUS);
					commonOperationsDao.insert(showPaymentMethod);
				}
			}

			for (ShowPaymentMethod showPaymentMethod : showPaymentMethodList) {
				boolean found = false;
				for (int i = 0; i < paymentMethodList.length; i++) {
					if (showPaymentMethod.getPaymentMethodId().compareTo(new BigInteger(paymentMethodList[i])) == 0) {
						found = true;
					}
				}
				if (!found) {
					ShowPaymentMethod showPaymentMethodUpdate = showPaymentMethodDao.getById(showPaymentMethod.getId());
					// showPaymentMethodUpdate.setStatus(DaoConstants.PASSIVE_STATUS);
					// commonOperationsDao.update(showPaymentMethodUpdate);
					commonOperationsDao.delete(showPaymentMethodUpdate);
				}
			}

		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse delete(BigInteger id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse getShowById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowGetByIdResponse serviceResponse = new ShowGetByIdResponse();
		Show show = showDao.getById(id);
		if (show == null) {
			errorCode = ServiceResponseConstants.GET_SHOW_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SHOW_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setDescription(show.getDescription());
			serviceResponse.setDuration(show.getDuration());
			serviceResponse.setEndDate(convetDateToString(show.getEndDate()));
			serviceResponse.setHallId(show.getHallId());
			serviceResponse.setId(show.getId());
			serviceResponse.setMaxTicketSaleCount(show.getMaxTicketSaleCount());
			serviceResponse.setName(show.getName());
			serviceResponse.setPriceListId(show.getPriceListId());
			serviceResponse.setSaleEndDate(convetDateToString(show.getSaleEndDate()));
			serviceResponse.setSaleStartDate(convetDateToString(show.getSaleStartDate()));
			serviceResponse.setShowTypeId(show.getShowTypeId());
			serviceResponse.setStartDate(convetDateToString(show.getStartDate()));
			serviceResponse.setStatus(show.getStatus());
			serviceResponse.setSummary(show.getSummary());
			serviceResponse.setVenueId(show.getVenueId());
			serviceResponse.setSeatingPlanId(show.getSeatingPlanId());
			serviceResponse.setShowPaymentMethods(showPaymentMethodDao.getPaymentMethodsByShowId(id));
			serviceResponse.setTicketCount(show.getTicketCount());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	private String convetDateToString(Date startDate) {
		return startDate.toString();
	}

	@Override
	public ServiceResponse getAllShows() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowGetAllListResponse serviceResponse = new ShowGetAllListResponse();
		List<Show> showList = showDao.getAllShows();
		if (showList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_SHOWS_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_SHOWS_ERROR_DESC;
		} else {
			serviceResponse.setShowList(showList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getShowByIdToCreateEvent(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		ShowGetByIdToCreateEventResponse serviceResponse = new ShowGetByIdToCreateEventResponse();
		Show show = showDao.getById(id);
		if (show == null) {
			errorCode = ServiceResponseConstants.GET_SHOW_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_SHOW_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setDuration(show.getDuration());
			serviceResponse.setId(show.getId());
			serviceResponse.setStartDate(convetDateToString(show.getStartDate()));
			String hallName = hallDao.getById(show.getHallId()).getName();
			serviceResponse.setHallName(hallName);
			String showTypeName = showTypeDao.getById(show.getShowTypeId()).getName();
			serviceResponse.setShowTypeName(showTypeName);
			String venueName = venueDao.getById(show.getVenueId()).getName();
			serviceResponse.setVenueName(venueName);
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
