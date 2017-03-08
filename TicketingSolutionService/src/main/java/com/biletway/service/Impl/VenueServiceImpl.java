package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import com.biletway.dao.entity.Venue;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.VenueDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.VenueService;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.response.VenueDeleteServiceResponse;
import com.biletway.service.response.VenueGetAllListResponse;
import com.biletway.service.response.VenueGetByIdResponse;
import com.biletway.service.response.VenueInsertServiceResponse;
import com.biletway.service.response.VenueUpdateServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class VenueServiceImpl implements VenueService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	VenueDao venueDao = (VenueDao) ApplicationContextUtil.getApplicationContext().getBean("venueDao");

	@Override
	public ServiceResponse insert(String name, BigInteger countryId, BigInteger stateId, BigInteger cityId,
			String address, String postalCode, String phone, String fax, String email, String venueUrl, String latitude,
			String longitude) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Venue venue = new Venue();
		venue.setAddress(address);
		venue.setCityId(cityId);
		venue.setCountryId(countryId);
		venue.setEmail(email);
		venue.setFax(fax);
		venue.setLatitude(latitude);
		venue.setLongitude(longitude);
		venue.setName(name);
		venue.setPhone(phone);
		venue.setPostalCode(postalCode);
		venue.setStateId(stateId);
		venue.setVenueUrl(venueUrl);
		venue.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		boolean insertResponse = commonOperationsDao.insert(venue);
		VenueInsertServiceResponse serviceResponse = new VenueInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_VENUE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_VENUE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse update(BigInteger id, String name, BigInteger countryId, BigInteger stateId,
			BigInteger cityId, String address, String postalCode, String phone, String fax, String email,
			String venueUrl, String latitude, String longitude) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Venue venue = venueDao.getById(id);
		venue.setAddress(address);
		venue.setCityId(cityId);
		venue.setCountryId(countryId);
		venue.setEmail(email);
		venue.setFax(fax);
		venue.setLatitude(latitude);
		venue.setLongitude(longitude);
		venue.setName(name);
		venue.setPhone(phone);
		venue.setPostalCode(postalCode);
		venue.setStateId(stateId);
		venue.setVenueUrl(venueUrl);
		venue.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(venue);
		VenueUpdateServiceResponse serviceResponse = new VenueUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_VENUE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_VENUE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse delete(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		Venue venue = new Venue();
		venue.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(venue);
		VenueDeleteServiceResponse serviceResponse = new VenueDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_VENUE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_VENUE_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getVenueById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		VenueGetByIdResponse serviceResponse = new VenueGetByIdResponse();
		Venue venue = venueDao.getById(id);
		if (venue == null) {
			errorCode = ServiceResponseConstants.GET_VENUE_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_VENUE_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setAddress(venue.getAddress());
			serviceResponse.setCityId(venue.getCityId());
			serviceResponse.setCountryId(venue.getCountryId());
			serviceResponse.setEmail(venue.getEmail());
			serviceResponse.setFax(venue.getFax());
			serviceResponse.setId(venue.getId());
			serviceResponse.setLatitude(venue.getLatitude());
			serviceResponse.setLongitude(venue.getLongitude());
			serviceResponse.setName(venue.getName());
			serviceResponse.setPhone(venue.getPhone());
			serviceResponse.setPostalCode(venue.getPostalCode());
			serviceResponse.setStateId(venue.getStateId());
			serviceResponse.setStatus(venue.getStatus());
			serviceResponse.setVenueUrl(venue.getVenueUrl());
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllVenues() {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		VenueGetAllListResponse serviceResponse = new VenueGetAllListResponse();
		List<Venue> venueList = venueDao.getAllList();
		if (venueList == null) {
			errorCode = ServiceResponseConstants.GET_VENUE_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_VENUE_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setVenueList(venueList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
