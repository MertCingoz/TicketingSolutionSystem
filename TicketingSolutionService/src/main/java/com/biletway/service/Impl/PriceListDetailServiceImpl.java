package com.biletway.service.Impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.biletway.dao.entity.PriceListDetail;
import com.biletway.dao.entity.PriceType;
import com.biletway.dao.entity.Show;
import com.biletway.dao.interfaces.CommonOperationsDao;
import com.biletway.dao.interfaces.PriceListDetailDao;
import com.biletway.dao.interfaces.PriceTypeDao;
import com.biletway.dao.interfaces.ShowDao;
import com.biletway.service.constants.ServiceResponseConstants;
import com.biletway.service.interfaces.PriceListDetailService;
import com.biletway.service.response.PriceListDetailForSaleScreen;
import com.biletway.service.response.PriceListDetailForSaleScreenResponse;
import com.biletway.service.response.PriceListDetailGetAllListResponse;
import com.biletway.service.response.PriceListDetailInsertServiceResponse;
import com.biletway.service.response.ServiceResponse;
import com.biletway.service.util.ApplicationContextUtil;
import com.biletway.service.util.ServiceConstants;
import com.biletway.service.util.ServiceHelper;

public class PriceListDetailServiceImpl implements PriceListDetailService {
	String errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
	String errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
	CommonOperationsDao commonOperationsDao = (CommonOperationsDao) ApplicationContextUtil.getApplicationContext()
			.getBean("commonOperationsDao");
	PriceListDetailDao priceListDetailDao = (PriceListDetailDao) ApplicationContextUtil.getApplicationContext()
			.getBean("priceListDetailDao");
	ShowDao showDao = (ShowDao) ApplicationContextUtil.getApplicationContext().getBean("showDao");
	PriceTypeDao priceTypeDao = (PriceTypeDao) ApplicationContextUtil.getApplicationContext().getBean("priceTypeDao");

	@Override
	public ServiceResponse insert(BigInteger priceTypeId, BigInteger priceListId, BigInteger seatCategoryId,
			double price) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceListDetail priceListDetail = priceListDetailDao.getPriceListDetail(priceListId, seatCategoryId,
				priceTypeId);
		boolean insertResponse = false;
		if (priceListDetail == null) {
			priceListDetail = new PriceListDetail();
			priceListDetail.setPrice(price);
			priceListDetail.setPriceListId(priceListId);
			priceListDetail.setPriceTypeId(priceTypeId);
			priceListDetail.setSeatCategoryId(seatCategoryId);
			priceListDetail.setStatus(ServiceConstants.DAO_ACTIVE_STATUS);
			insertResponse = commonOperationsDao.insert(priceListDetail);
		} else {
			priceListDetail.setPrice(price);
			insertResponse = commonOperationsDao.update(priceListDetail);
		}
		PriceListDetailInsertServiceResponse serviceResponse = new PriceListDetailInsertServiceResponse();
		serviceResponse.setResult(insertResponse);
		if (!insertResponse) {
			errorCode = ServiceResponseConstants.CREATE_PRICE_LIST_DETAIL_ERROR_CODE;
			errorDesc = ServiceResponseConstants.CREATE_PRICE_LIST_DETAIL_ERROR_DESC;
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getPriceListDetailList(BigInteger priceListId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();
		PriceListDetailGetAllListResponse serviceResponse = new PriceListDetailGetAllListResponse();
		List<PriceListDetail> priceListDetailList = priceListDetailDao.getPriceListDetailList(priceListId);
		if (priceListDetailList == null) {
			errorCode = ServiceResponseConstants.GET_PRICE_LIST_DETAIL_LIST_ERROR_CODE;
			errorDesc = ServiceResponseConstants.GET_PRICE_LIST_DETAIL_LIST_ERROR_DESC;
		} else {
			serviceResponse.setPriceListDetailList(priceListDetailList);
		}
		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

	@Override
	public ServiceResponse getPriceListDetailByShowId(BigInteger showId) {
		errorCode = ServiceResponseConstants.ERROR_CODE_SUCCESS;
		errorDesc = ServiceResponseConstants.ERROR_DESC_SUCCESS;
		long startTime = System.currentTimeMillis();

		PriceListDetailForSaleScreenResponse serviceResponse = new PriceListDetailForSaleScreenResponse();
		List<PriceListDetailForSaleScreen> priceListDetailList = new ArrayList<PriceListDetailForSaleScreen>();

		// get show info by id
		Show show = showDao.getById(showId);

		// get priced price list detail
		List<PriceListDetail> pricedPriceListDetailList = priceListDetailDao
				.getPricedPriceListDetail(show.getPriceListId());

		for (int i = 0; i < pricedPriceListDetailList.size(); i++) {
			PriceListDetailForSaleScreen newob = new PriceListDetailForSaleScreen();
			PriceType pt = priceTypeDao.getById(pricedPriceListDetailList.get(i).getPriceTypeId());
			newob.setName(pt.getName());
			newob.setPrice(pricedPriceListDetailList.get(i).getPrice());
			newob.setPriceTypeId(pricedPriceListDetailList.get(i).getPriceTypeId());
			priceListDetailList.add(newob);
		}

		serviceResponse.setPriceListDetailList(priceListDetailList);

		long endTime = System.currentTimeMillis();
		long serviceDuration = endTime - startTime;
		return ServiceHelper.getServiceResponseObject(errorCode, errorDesc, serviceDuration, serviceResponse);
	}

}
