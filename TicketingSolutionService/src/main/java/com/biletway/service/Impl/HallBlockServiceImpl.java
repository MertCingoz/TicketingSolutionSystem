package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Component;

import com.biletway.dao.entity.HallBlock;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.HallBlockDao;
import com.biletway.dao.util.DaoConstants;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.HallBlockService;
import com.biletway.service.response.HallBlockDeleteServiceResponse;
import com.biletway.service.response.HallBlockGetAllListByHallIdResponse;
import com.biletway.service.response.HallBlockGetByIdResponse;
import com.biletway.service.response.HallBlockInsertServiceResponse;
import com.biletway.service.response.HallBlockUpdateServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

@Component
public class HallBlockServiceImpl implements HallBlockService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	HallBlockDao hallBlockDao = (HallBlockDao) ApplicationContextUtil.getApplicationContext().getBean("hallBlockDao");

	@Override
	public ServiceResponse insert(String name, BigInteger venueId, BigInteger hallId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		HallBlock hallBlock = new HallBlock();
		hallBlock.setHallId(hallId);
		hallBlock.setName(name);
		hallBlock.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
		hallBlock.setVenueId(venueId);
		boolean insertResponse = commonOperationsDao.insert(hallBlock);
		HallBlockInsertServiceResponse serviceResponse = new HallBlockInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_HALL_BLOCK_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_HALL_BLOCK_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);

	}

	@Override
	public ServiceResponse update(BigInteger id, String name) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		HallBlock hallBlock = hallBlockDao.getById(id);
		hallBlock.setName(name);
		hallBlock.setStatus(DaoConstants.ACTIVE_STATUS);
		boolean updateResponse = commonOperationsDao.update(hallBlock);
		HallBlockUpdateServiceResponse serviceResponse = new HallBlockUpdateServiceResponse();
		serviceResponse.setResult(updateResponse);
		if (!updateResponse) {
			errorCode = ServiceResponseConstants.UPDATE_HALL_BLOCK_ERROR_CODE;
			errorDesc = ServiceResponseConstants.UPDATE_HALL_BLOCK_ERROR_DESC;
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
		HallBlock hallBlock = new HallBlock();
		hallBlock.setId(id);
		boolean deleteResponse = commonOperationsDao.delete(hallBlock);
		HallBlockDeleteServiceResponse serviceResponse = new HallBlockDeleteServiceResponse();
		serviceResponse.setResult(deleteResponse);
		if (!deleteResponse) {
			errorCode = ServiceResponseConstants.DELETE_HALL_BLOCK_ERROR_CODE;
			errorDesc = ServiceResponseConstants.DELETE_HALL_BLOCK_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getHallBlockById(BigInteger id) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		HallBlockGetByIdResponse serviceResponse = new HallBlockGetByIdResponse();
		HallBlock hallBlock = hallBlockDao.getById(id);
		if (hallBlock == null) {
			errorCode = ServiceResponseConstants.GET_HALL_BLOCK_BY_ID_IS_NULL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_HALL_BLOCK_BY_ID_IS_NULL_ERROR_DESC;
		} else {
			serviceResponse.setId(hallBlock.getId());
			serviceResponse.setName(hallBlock.getName());
			serviceResponse.setStatus(hallBlock.getStatus());
			serviceResponse.setVenueId(hallBlock.getVenueId());
			serviceResponse.setHallId(hallBlock.getHallId());
		}

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getAllHallBlocksByHallId(BigInteger hallId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		HallBlockGetAllListByHallIdResponse serviceResponse = new HallBlockGetAllListByHallIdResponse();
		List<HallBlock> hallBlockList = hallBlockDao.getAllHallBlocksByHallId(hallId);
		if (hallBlockList == null) {
			errorCode = ServiceResponseConstants.GET_ALL_HALL_BLOCK_BY_VENUE_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_ALL_HALL_BLOCK_BY_VENUE_ERROR_DESC;
		} else {
			serviceResponse.setHallBlockList(hallBlockList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
